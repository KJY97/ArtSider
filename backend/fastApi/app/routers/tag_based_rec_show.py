from contextlib import nullcontext
import numpy as np
from numpy import dot
from numpy.linalg import norm
import pandas as pd
from collections import Counter
import pymysql
from sqlalchemy import create_engine
from urllib import parse
from tqdm import tqdm

from fastapi import APIRouter

router = APIRouter()

@router.get("/recommand/userTag/{userId}/{category}")
def hello(userId, category):
    
    # DB 연결
    password = 'bigdata@202'
    pwd = parse.quote_plus(password)

    db_connection_str = 'mysql+pymysql://root:'+pwd+'@j6b202.p.ssafy.io/artsider'
    db_connection_engine = create_engine(db_connection_str, encoding='utf-8')

    # mysql 연결 후 pandas read
    connection = db_connection_engine.connect()


    # 코사인 유사도 계산 메소드
    def cos_sim(A, B):
        return dot(A, B)/(norm(A)*norm(B))

    # 유사도 계산 시 사용할 키워드 컬럼
    keywords = [
        '좋아요', '훌륭해요',  '재관람', '아이들', '신나요', '노래', '춤', '선녀님', '추억', '행복',
        '감동', '가족', '강추', '목욕탕', '홍광호', '정원철', '김재현', '신성주', '윤공주', '최수진', '신성록', '류정한', 
    ] + ['좋아요', '꿀잼', '대박', '믿고보는 배우', 'N차 방문', '미친 연기력', '웃음주의', '강추', '즐거운', '시간순삭',
    '최고', '고막 힐링', '꿀잼보장', '친구', '인상적인', '입문자용', '아이', '가족', '몰입', '뮤지컬', '만족', '감동', 
    '추리', '볼만한', '기대가 되는', '신선한', '슬픔', '공포', '커플', '사랑', '미친', '행복', '소통형 연극'
    , '감탄', '완벽', '배우가 멋진', '힐링', '배우가 이쁜', '소름', '어른', '혼자 갈 만한', '영화', '극장',
    '서울', '따뜻한', '로맨스', '대학로', '원작', '연애', '맡', '개그', '소극장', '댄스머신', '고요한']

    keywords = list(set(keywords))


    # uri의 카테고리 값과 DB의 카테고리 값 map 저장
    category_Map = {
            "MU" : "뮤지컬",
            "CO" : "콘서트",
            "CL" : "클래식/오페라",
            "DA" : "무용/전통예술",
            "DR" : "연극",
            "FA" : "아동/가족"
        }


    '''userId를 활용하여 필요한 데이터 정제'''

    # userId의 선호 태그 목록 조회
    user_tag = pd.read_sql(f"SELECT * FROM user_tag WHERE user_id ={userId};", connection)


    # userId의 선호 태그 리스트
    user_tag_list = user_tag['tag_content'].tolist()


    # userId의 top3 선호 태그 리스트
    user_tag_top3 = user_tag.sort_values('weight', ascending=False).head(3)
    tag_top3_list = user_tag_top3['tag_content'].tolist()


    # 키워드 컬럼과 비교하여 유저 선호 태그들의 유무 표시(1 or 0)
    temp_list= [0 for i in range(len(keywords))] # 0으로 list 초기화
    for tag in user_tag_list:
        tag_idx = keywords.index(tag)
        temp_list[tag_idx] = 1
        

    # 코사인 유사도에서 사용될 userId의 선호 태그 numpy 배열
    user_doc = np.array(temp_list)


    # userID의 관심 공연 목록 조회
    favorite = pd.read_sql(f"SELECT show_id FROM favorite WHERE user_id ={userId};", connection)

    

    '''category를 활용하여 필요한 데이터 정제'''

    # 카테고리에 해당하는 모든 공연의 태그 정보와 공연 포스터 가져오기
    show = pd.read_sql("SELECT p.id, st.tag_content, st.weight, p.poster_path "
                    + "FROM show_tag st "
                    + "INNER JOIN performance p "
                    + "ON st.show_id = p.id "
                    + f"WHERE category LIKE '{category_Map.get(category)}';", connection)
    
    
    # userId의 관심 공연에 등록된 공연들 제외
    show = show[~show['id'].isin(favorite['show_id'])]


    # category에 속하는 공연들 번호 리스트(태그로 인해 값이 중복되므로 중복 제거)
    performance_id_list = show['id'].drop_duplicates().tolist() 

    
    ### show 태그 테이블을 한 행씩 돌면서 user와 코싸인 유사도 계산 ###
    cos_data = []
    for row in tqdm(performance_id_list):
        # show 테이블에서 performance_id의 한 행
        show_by_id = show.loc[show['id'] == row]

        # performance_id의 태그 리스트
        show_tag_list = show_by_id['tag_content'].tolist()
        
        # performance_id의 태그 numpy 배열
        temp_list= [0 for i in range(len(keywords))]
        for tag in show_tag_list:
            tag_idx = keywords.index(tag)
            temp_list[tag_idx] = 1
        show_doc = np.array(temp_list)
    
        # 코사인 유사도 계산
        cos_result = cos_sim(user_doc, show_doc)
        cos_data.append([row, cos_result])


    # 코싸인 유사도 = user의 태그와 공연별 태그의 겹치는 정도
    # top3 태그별로 공연을 뽑아야하기 때문에 performance_id 컬럼을 추가함
    cossin_frame_by_userNshow = pd.DataFrame(data=cos_data, columns=['id',f'{userId}와의 유사도'])

    
    # 공연별 태그 테이블과 코사인 유사도 결과 테이블 합치기
    merge_frame = pd.merge(show, cossin_frame_by_userNshow)


    # 유사도가 높은 순서대로 정렬(오름차순 정렬)
    sort_frame = merge_frame.sort_values(f'{userId}와의 유사도', ascending=False)
    
    
    # # top3에 있는 태그들을 가지고 있는 공연들만 가져오기
    filter_by_top3_tag = sort_frame[sort_frame['tag_content'].isin(tag_top3_list)]

    
    # # top3 태그별 공연 추천 
    reco_show_by_tag = {}
    for tag in tag_top3_list:

        # tag를 가지는 공연 20개 
        show_by_tag = filter_by_top3_tag.loc[filter_by_top3_tag['tag_content'] == tag].head(20)

        # 공연들의 번호와 이미지 경로들만 저장
        show_by_tag = show_by_tag[['id', 'poster_path']]
        
        # 각 행을 딕셔너리 형태로 저장
        tmp_list = []
        for idx in show_by_tag.index:
            tmp_list.append(show_by_tag.loc[idx].to_dict())
        
        reco_show_by_tag[tag] = tmp_list
    
    print(reco_show_by_tag)
    print("추천 종료")

    return reco_show_by_tag