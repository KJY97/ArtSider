import pandas as pd
import numpy as np
from sklearn.metrics.pairwise import cosine_similarity
import pymysql

from fastapi import APIRouter

router = APIRouter()


@router.get("/recommendation", status_code=200)
async def read_root():
    #  데이터 연결
    connection = pymysql.connect(host='j6b202.p.ssafy.io', port=3306,
                                 user='root', password='bigdata@202', db='artsider', charset='utf8')

    shows = pd.read_sql("SELECT * FROM performance", connection)
    users = pd.read_sql("SELECT * FROM user", connection)
    reviews = pd.read_sql("SELECT * FROM review", connection)
    # as_index는 grouby를 쓰면 기본으로 그룹 라벨이 index가 되는데 index를 사용하기 싫으면 as_index=False를 설정하면 된다.
    # rating을 평균낸 것 = Mean
    # nummpy의 mean 함수로 평균 구해주기
    Mean = reviews.groupby(by="user_id", as_index=False)['rating'].mean()

    # merge는 두 데이터프레임을 각 데이터에 존재하는 고유값(key)를 기준으로 병합할 때 사용한다.
    # 보통은 on=None이 디폴트 값인데 여기서는 userID를 기준으로 Ratings와 Mean을 병합해줬음.
    Rating_avg = pd.merge(reviews, Mean, on='user_id')

    # rating_x는 유저 A가 공연 A에 준 평점 값
    # rating_y는 공연 A의 전체 평점의 평균
    # adg_rating값은 유저 A가 공연 A의 준 평점 값 - 공연 A의 전체 평점의 평균(리뷰를 통해 해당 공연에 관한 리뷰의 평점만 가져온 평균을 의미)
    # 만약 adg_rating이 0보다 크다면 유저는 그 공연을 추천하고 있다는 뜻.
    # ex) Rating_avg.head()에서 head는 이제 데이터 프레임 안의 처음 n줄의 데이터를 출력한다.
    # 따로 입력 안해주면 기본값은 5개 , 마지막 n줄의 데이터 출력은 tail()
    # score
    Rating_avg['adg_rating'] = Rating_avg['rating_x']-Rating_avg['rating_y']

    # pivot_table은 많은 양의 데이터 중 필요한 자료만 뽑아 새롭게 표를 작성해 주는 기능 Like 엑셀
    # pivot_table() 함수의 기본 구성 요소
    # 행 인덱스 index : user_Id
    # 열 인덱스 columns : show_id
    # 데이터 값 values : rating_x
    # pd.pivot_table의 경우 aggfunc를 통해서 함수를 정의할 수 있다.
    # 값이 없는 경우 NaN(Not a Number)으로 출력
    check = pd.pivot_table(Rating_avg, values='rating_x',
                           index='user_id', columns='show_id')

    # 평점 값 대신 각각의 칸에 adg_rating을 넣어줌.
    final = pd.pivot_table(Rating_avg, values='adg_rating',
                           index='user_id', columns='show_id')

    # Replacing NaN by Show Rationg Average
    # fillna는 결측값(Missing Value)을 채우는 함수
    # axis= 0 : 행을 기준으로 결측값을 채운다.
    # axis= 1 : 열을 기준으로 결측값을 채운다.
    # 결측값 = None이나 NaN으로 출력되는 값이 기록되지 않았거나 관측되지 않은 경우 데이터에 저장되는 값
    # Replacing NaN by user Average
    final_show = final.fillna(final.mean(axis=0))

    # user 기준으로 집계를 해준다
    final_user = final.apply(lambda row: row.fillna(row.mean()), axis=1)

    # user similarity on replacing NAN by user avg
    # 사용자간 유사도, NAN을 유저 평균으로 대체한것
    b = cosine_similarity(final_user)
    # sklearn에서 코사인유사도 구하는 함수를 제공, 사용
    # 대각선 값을 모두 0으로 바꿔줌 왜냐하면 자기 자신과 자기 자신의 유사도는 0으로 설정해줘야 함.
    np.fill_diagonal(b, 0)

    # 위에서 정의한 final_user의 index를 이용해서 유저 인덱스를 저장해줌
    similarity_with_user = pd.DataFrame(b, index=final_user.index)
    similarity_with_user.columns = final_user.index

    # user similarity on replacing NAN by item(movie) avg
    # 사용자간 유사도, NAN을 공연 평균으로 대체한것
    # 사용자 간의 코사인 유사도
    cosine = cosine_similarity(final_show)
    np.fill_diagonal(cosine, 0)
    similarity_with_show = pd.DataFrame(cosine, index=final_show.index)

    # 위에서 정의한 final_show의 index를 이용해서 영화 인덱스를 저장해줌
    similarity_with_show.columns = final_user.index

    # 유사도 행렬과 n 값을 입력으로 사용하고 모든 사용자에 대해 가장 가까운 n개의 이웃을 반환하는 함수

    def find_n_neighbours(df, n):
        order = np.argsort(df.values, axis=1)[:, :n]
        df = df.apply(lambda x: pd.Series(x.sort_values(ascending=False)
                                          .iloc[:n].index,
                                          index=['top{}'.format(i) for i in range(1, n+1)]), axis=1)
        return df

    # top 30 neighbours for each user
    sim_user_30_m = find_n_neighbours(similarity_with_show, 30)

    def get_user_similar_shows(user1, user2):
        common_shows = Rating_avg[Rating_avg.user_id == user1].merge(
            Rating_avg[Rating_avg.user_id == user2],
            on="show_id",
            how="inner")
        return common_shows

    Rating_avg = Rating_avg.astype({"show_id": str})
    Show_user = Rating_avg.groupby(by='user_id')[
        'show_id'].apply(lambda x: ','.join(x))

    # 유저별 공연 추천 함수 User_item_score

    def User_item_score(user):
        # nonta는 값이 있는지 없는지 확인하는 함수
        # any()는 모든 값이 있으면 True, 없으면 False
        # tolist()는 리스트로 변환
        show_seen_by_user = check.columns[check[check.index == user].notna(
        ).any()].tolist()
        a = sim_user_30_m[sim_user_30_m.index == user].values

        # squeeze()는 배열의 차원 축소 [[1, 2, 3, 4]]를 [1, 2, 3, 4]로 변환
        b = a.squeeze().tolist()

        # isin()은 열이 list의 값을 포함하고 있는 모든 행들을 골라 낼때 주로 쓰임 -> 있는지 없는지 확인
        d = Show_user[Show_user.index.isin(b)]
        z = ','.join(d.values)
        shows_seen_by_similar_users = z.split(',')
        shows_under_consideration = list(
            set(shows_seen_by_similar_users)-set(list(map(str, show_seen_by_user))))

        if shows_under_consideration == ['']:
            shows_under_consideration = []

        shows_under_consideration = list(map(int, shows_under_consideration))

        score = []
        for item in shows_under_consideration:
            c = final_show.loc[:, item]
            d = c[c.index.isin(b)]
            f = d[d.notnull()]
            avg_user = Mean.loc[Mean['user_id'] == user, 'rating'].values[0]
            index = f.index.values.squeeze().tolist()
            corr = similarity_with_show.loc[user, index]
            fin = pd.concat([f, corr], axis=1)
            fin.columns = ['adg_score', 'correlation']
            fin['score'] = fin.apply(
                lambda x: x['adg_score'] * x['correlation'], axis=1)
            nume = fin['score'].sum()
            deno = fin['correlation'].sum()
            final_score = avg_user + (nume/deno)
            score.append(final_score)
        data = pd.DataFrame(
            {'show_id': shows_under_consideration, 'score': score})

        top_recommendation = data.sort_values(
            by='score', ascending=False)

        show_Name = top_recommendation.merge(
            reviews, how='inner', on='show_id')
        show_Names = show_Name.show_id.values.tolist()

        return show_Names

    user_id_list = list(users.user_id)
    cursor = connection.cursor(pymysql.cursors.DictCursor)

    # db 데이터 비우기
    cursor.execute("DELETE FROM recommendation")

    # auto increment 초기화
    cursor.execute("ALTER TABLE recommendation AUTO_INCREMENT = 1")

    for user_id in user_id_list:

        predicted_shows = User_item_score(user_id)

        if predicted_shows == False:
            continue

        show_list = []
        for show in predicted_shows:
            if show not in show_list:
                show_list.append(show)

                # 10개 넘으면 pass
                if len(show_list) == 10:
                    break

        # db에 넣기
        if show_list == []:
            continue
        print(user_id, show_list)

        for show_id in show_list:
            sql = "INSERT INTO recommendation (show_id,user_id) VALUES (%s, %s)"
            cursor.execute(sql, (show_id, user_id))

    connection.commit()
    cursor.close()

    print("saved all")
