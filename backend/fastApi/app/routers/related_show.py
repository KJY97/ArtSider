import numpy as np
from numpy import dot
from numpy.linalg import norm
import pandas as pd
from sqlalchemy import create_engine
from urllib import parse
from tqdm import tqdm

from fastapi import APIRouter

router = APIRouter()

@router.get("/recommand/relatedShow", status_code=200)
def related_show():
    password = 'bigdata@202'
    pwd = parse.quote_plus(password)

    db_connection_str = 'mysql+pymysql://root:'+pwd+'@j6b202.p.ssafy.io/artsider'
    db_connection_engine = create_engine(db_connection_str, encoding='utf-8')

    def cos_sim(A, B):
        return dot(A, B)/(norm(A)*norm(B))

    keywords = [
        '좋아요', '훌륭해요',  '재관람', '아이들', '신나요', '노래', '춤', '선녀님', '추억', '행복',
        '감동', '가족', '강추', '목욕탕', '홍광호', '정원철', '김재현', '신성주', '윤공주', '최수진', '신성록', '류정한', 
    ] + ['좋아요', '꿀잼', '대박', '믿고보는 배우', 'N차 방문', '미친 연기력', '웃음주의', '강추', '즐거운', '시간순삭',
    '최고', '고막 힐링', '꿀잼보장', '친구', '인상적인', '입문자용', '아이', '가족', '몰입', '뮤지컬', '만족', '감동', 
    '추리', '볼만한', '기대가 되는', '신선한', '슬픔', '공포', '커플', '사랑', '미친', '행복', '소통형 연극'
    , '감탄', '완벽', '배우가 멋진', '힐링', '배우가 이쁜', '소름', '어른', '혼자 갈 만한', '영화', '극장',
    '서울', '따뜻한', '로맨스', '대학로', '원작', '연애', '맡', '개그', '소극장', '댄스머신', '고요한']

    keywords = list(set(keywords))

    connection = db_connection_engine.connect()
    tag_data = pd.read_sql("SELECT * FROM show_tag;", connection)

    old_index_list = tag_data['show_id'].tolist() # 인덱스 설정
    index_list = []
    for idx in old_index_list:
        if idx not in index_list and old_index_list.count(idx) >= 5:
            index_list.append(idx)
    columns = keywords # 컬럼 설정

    data = []
    for idx in tqdm(index_list):
        show_tag_data =  pd.read_sql(f"SELECT * FROM show_tag WHERE show_id={idx};", connection)
        show_tag_list = show_tag_data['tag_content'].tolist()
        temp_list= [0 for i in range(len(keywords))]
        for tag in show_tag_list:
            tag_idx = keywords.index(tag)
            temp_list[tag_idx] = 1
        data.append(np.array(temp_list))

    df1 = pd.DataFrame(data=data, columns=columns, index=index_list)


    cos_data = []
    for show1_idx in tqdm(df1.index):
        doc1 = np.array(df1.loc[show1_idx])
        temp = []
        for show2_idx in df1.index:
            if show1_idx == show2_idx:
                temp.append(0)
                continue
            
            doc2 = np.array(df1.loc[show2_idx])
            cos_result = cos_sim(doc1, doc2)
            temp.append(cos_result)
        cos_data.append(temp)

        
    df2 = pd.DataFrame(data=cos_data, columns=index_list, index=index_list)
    

    param = 0.7

    related_show_table = 'related_show'

    related_show_columns = ['show_id', 'related_show_id'] 
    related_show_data = []

    for row_id in df2.index:
        temp = []
        for col_id in df2.index:
            if row_id == col_id:
                continue
            
            if df2.loc[row_id, col_id] > param:
                temp.append(col_id)

        # 정렬
        sorted_temp = sorted(temp, key=lambda x: -df2.loc[row_id, x])[:4]
        
        # 데이터 정제
        for related_show_id in sorted_temp:
            related_show_data.append([row_id, related_show_id])


    related_show_df = pd.DataFrame(data=related_show_data, columns=related_show_columns)

    # DB 저장
    related_show_df.to_sql(name=related_show_table, con=connection, if_exists='append', index=False)

    pd.read_sql("SELECT * FROM related_show;", connection)
    connection.close()

    print("related_show saved all")