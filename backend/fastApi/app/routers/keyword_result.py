import pandas as pd
from konlpy.tag import Mecab
from collections import Counter
import re
from sqlalchemy import create_engine
from urllib import parse

from fastapi import APIRouter

router = APIRouter()

@router.get("/recommand/keyword", status_code=200)
def show_keyword():

    password = 'artsider202!'
    pwd = parse.quote_plus(password)

    db_connection_str = 'mysql+pymysql://artsider:'+pwd+'@j6b202.p.ssafy.io/artsider'
    db_connection_engine = create_engine(db_connection_str, encoding='utf-8')


    # mysql 연결 후 pandas read
    # db_connection = db_connection_engine.connect()
    connection = db_connection_engine.connect()

    data = pd.read_sql("SELECT * FROM review;", connection)


    # 데이터 전처리
    # 한글만 선별
    for i in data.index:
        s = re.sub("[^ㄱ-ㅎㅏ-ㅣ가-힣 ]", "", str(data['contents'][i]))
        data.loc[i, 'contents'] = s    

    mecab = Mecab(dicpath=r"C:/mecab/mecab-ko-dic")

    used_words = []
    for k in data.index:
        review = str(data['contents'][k])
        if review:
            temp = mecab.pos(review)
            used_words += temp
            
    # with open('./words.txt', 'w') as f:
    #     for word in used_words:
    #         f.write(word[0] + ', ' + word[1] +'\n')


    stopwords = '에 는데 이렇게 라도 어서 에서 이 어서 어요 과 는 부터 것 을 어요 고 게 던 은 었 내내 룽 도 고 ㅋ ㅎ 나오 는 님 했 던 보다 ' + '장이 였 지금 없 어 져서 을 수 습니다 그러나 잠시 나마 통해 그 를 해 줄 합니다 제 가 더 한 거 같 아요 들 십니다 받 았 의 사 세요 '+ '는데 나 다 좀 네요 보단 큰 더 구요 라 살 으로 주 을 많이 했었 얘길 드라구요 느라 나 셨 한 분 며 시 젼 러 겁니다요 요 쿠르트 ㅎㅎ ㅋㅋ '+ '생겼 되 떼 라구 더라구요 하 시 ㅠ ㅠㅠ 제가 ㅠ제가 나요 년 맡 요배 멋지 '+ '구성 주변 말 개인 적극 말 다니 더욱 누구 형 마지막 대사 난쟁이 중간 끝 결말 작품 엔딩 이야기 모두 사람 같이 스토리 내용 함께 생각 별 관객 정말 공연 연극 같 많이 많 오늘 고민 역할 제일 자리 봐야 비 높 6 준 틈 봐 전개 전체 팀 ... 조명 하루 포인트 그렇게 진행 10 사이 매우 그대로 꽉 서로 남자 여자 우리 여러 차 < 〉 질문 임 반 건물 7 물 작 몰 대한 한다 손 쇼 운 끝나 간만에 # 잊 오래 초반 으며 살짝 제대로 그렇 위해 보냈 인터 씨 는데요 사건 난 갈게요 ~!! 아무 또한 스러운 물론 못했 하지만 작가 오랫만 아닌 려구요 놓 뿐 ;&# 다만 생각나 감 거리 선녀 해야 〈 언제 장치 거의 달 생활 팬 귀 가볍 떡볶이 주인공 건데  뻔 티켓 직접 케 ㅜㅜ 뻔 필요 려고 진 삶 터지 오백 매번 매 으신 ㅠㅠㅠ 약간 느끼 얘기 다르 르 났 층 느라 선녀님 편 항상 화이팅 바뀌 담 저희 나가 상황 요소 돈 이제 으니 수지 그런지 풀 씩 느낄 웨스턴 늘 끼리 넘치 을까 쓰 가득 으시 딱 미오 와서 디아길레프 몰랐 습니당 막 합 믿 페어 덕 께 됩니다 월 빨래 만나 &# 그래서 위 생기 입 사진 존 > 답니다 은데 실 보이 가장 ; 열 사실 먹 수탕 더라고요 이유 힘들 듣 찍 좌석 어떤 무엇 빵빵 라서 용 어떻게 홍광호 큰 갈 니까 스럽 홍지 됐 속 으면서 근데 잡 어용 바로 줄기 아직 그래도 따라 ♥ ♡ 봐서 / 동안 선물 다면 자체 한번 가지 성 개 드립니다 순간 보내 기회 현실 지금 이나 공연장 4 찾 걱정 선택 처럼 조지 야 온 몇 5 크 눈 한테 앉 ~^^ 후 갑니다 - !!!! 명 이해 읽 뭐 백 요즘 죠 인지 너무나 등 캐릭터 ~~~ 장 나왔 그런 ㅠ 만큼 을 될 점 "" 자 맞 여 앤 ~! 이상 ... 간 참 걸 ㅎㅎㅎ 집 그냥 세 맨 을 네 덕분 밋 하이드 구요 긴 멀티 굿 이런 엔 구 3 ㅎ 마다 보여 역 아주 예요 라면 앞 째 킬 왜 삼 년 이렇게 골 엄청 이번 드문 십 지루 대학 모든 때문 함 건 을 기분 모습 알사탕 시작 느낌 봐도 더라구요 계속 인데 없이 남 : 특히 후기 못 듯 라고 만들 는지 코로나 된 마음 나르 길 하나 으면 라는 트 !!! 날 나오 살 책 지킬 조금 저 두 ) 다가 랑 줄 ~~ 며 말 좀 아서 다고 갔 전 신 넘버 참여 알 그리고 보다 쉬 제 해요 셨 2 모르 부터 예매 이랑 에게 중 받 재 ㅠㅠ 정도 아니 ( 치 내 1 에요 내내 다는 부분 음 일 안 할 면 해서 ^^ 입니다 왔 인 까지 때 면서 다른 세요 겠 !! 그 오랜만 무대 본 넘 셔서 요 이 다 아 고 는 어 연 니 하 지 에 도 서 가 보 들 기 무 로 게 극 너 시 있 을 우 ㅋ 습 나 었 배 만 데 은 한 의 리 밌 라 해 공 정 스 대 미 분 사 . ! , 아요 어요 습니다 너무 잘 는데 님 봤 했 네요 았 를 과 ~ 것 으로 싶 수 주 되 합니다 던 에서 ? 지만 러 우분 적 번 ㅋㅋ 관람 .. 거 였 어서 없 핵 ㅎㅎ 않 ㅋㅋㅋ'

    stopwords = stopwords.split()
    DA_list = [word[0] for word in used_words if word[0] not in stopwords]


    keyword = [
        '좋아요', '훌륭해요',  '재관람', '아이들', '신나요', '노래', '춤', '선녀님', '추억', '행복',
        '감동', '가족', '강추', '목욕탕', '홍광호', '정원철', '김재현', '신성주', '윤공주', '최수진', '신성록', '류정한', 
    ] + ['좋아요', '꿀잼', '대박', '믿고보는 배우', 'N차 방문', '미친 연기력', '웃음주의', '강추', '즐거운', '시간순삭',
    '최고', '고막 힐링', '꿀잼보장', '친구', '인상적인', '입문자용', '아이', '가족', '몰입', '뮤지컬', '만족', '감동', 
    '추리', '볼만한', '기대가 되는', '신선한', '슬픔', '공포', '커플', '사랑', '미친', '행복', '소통형 연극'
    , '감탄', '완벽', '배우가 멋진', '힐링', '배우가 이쁜', '소름', '어른', '혼자 갈 만한', '영화', '극장',
    '서울', '따뜻한', '로맨스', '대학로', '원작', '연애', '맡', '개그', '소극장', '댄스머신', '고요한']

    keyword = set(keyword)


    now_dict = {
        # 감정, 분위기 관련
        '좋' : '좋아요', '너무너무' : '좋아요', '짱짱' : '좋아요', '짱':'좋아요',
        '재밌' : '꿀잼','재미있' : '꿀잼', '잼' : '꿀잼', '재미' : '꿀잼', '꿀' : '꿀잼', '흥미':'꿀잼',
        '웃' : '웃음주의', '웃음' : '웃음주의', '웃기': '웃음주의', '코믹' : '웃음주의', '웃긴' : '웃음주의', '웃겨요': '웃음주의', '코미디':'웃음주의', '배꼽':'웃음주의','웃겼':'웃음주의','웃겨서':'웃음주의',
        '와' : '대박', '진짜' : '대박', '오' : '대박', '대단' : '대박', '강력':'대박', '역시':'대박', 
        '볼' : '볼만한', '괜찮':'볼만한',
        '즐겁' : '즐거운', '즐거웠': '즐거운', '신나': '즐거운', '스트레스' : '즐거운', '즐길': '즐거운', '즐기':'즐거운', 
        '매드니스' : '미친', '매드': '미친', '미쳤':'미친',
        '유쾌' : '꿀잼',
        '눈물' : '슬픔', '울' : '슬픔',
        '신선' : '신선한', '새로운':'신선한','신기':'신선한','다양':'신선한','센스':'신선한',
        '무섭' : '공포', '무서운' : '공포',
    #     '아쉽' : '아쉬운', '아쉬웠': '아쉬운', '후회' :'아쉬운',
        '따뜻' : '따뜻한',
        '빠지' : '몰입', '열연' : '몰입', '공감' : '몰입', '집중' : '몰입', '여운':'몰입',
        '반전' : '소름',
        '놀라' : '감탄', '깜짝':'감탄',
        '탄탄' : '완벽',
        '궁금' : '추리', '범인' : '추리',
        '매력' : '매력적인',
        '고요' : '고요한',
        '기대' : '기대가 되는',
        '감사' : '만족',
        # 출연진 관련
        '배우' : '믿고보는 배우', '연출':'믿고보는 배우', '분위기':'믿고보는 배우','캐스팅':'믿고보는 배우', '연기자':'믿고보는 배우',
        '멋있' : '배우가 멋진','멋진':'배우가 멋진',
        '예쁘' : '배우가 이쁜', '이쁘' : '배우가 이쁜',
        '연기' : '미친 연기력', '연기력' : '미친 연기력', '열정':'미친 연기력','애드립':'미친 연기력','표현':'미친 연기력','감정':'미친 연기력',
        '춤' : '댄스머신',

        # 장르 관련
        '시간' : '시간순삭',
        '또' : 'N차 방문', '더' : 'N차 방문', '다음': 'N차 방문','다시': 'N차 방문', '자주': 'N차 방문',
        '추천' : '꿀잼보장',
        '처음' : '입문자용', '첫' : '입문자용',
        '꼭' : '강추', '완전' : '강추','박수':'강추','지인':'강추',
        '노래' : '고막 힐링', '매력' : '고막 힐링', '소리': '고막 힐링','목소리':'고막 힐링','음악':'고막 힐링',
        '소통' : '소통형 연극', '호응': '소통형 연극',
        '기억' : '인상적인', '장면': '인상적인', '추억':'인상적인','인생':'인상적인','열심히':'인상적인','호흡':'인상적인','인상':'인상적인',
        # 인원 관련
        '어린이' : '아이', '아기' : '아이', 
        '남편' : '가족', '아들' : '가족', '딸' : '가족', '부모' : '가족', '아빠' : '가족', '엄마' : '가족', '형':'가족',
        '남친' : '커플', '연인' : '커플', '데이트':'커플',
        '혼자' : '혼자 갈 만한', 
        '둘' : '친구'
    }

    new_dict = {
        '좋': '좋아요', '즐거웠': '좋아요', '대박': '좋아요', '최고': '좋아요',
        '남편': '가족', '아내': '가족', '아들': '가족', '딸': '가족', '사촌': '가족', '할머니': '가족', 
        '행복': '행복', 
        '멋지':'훌륭해요', '수준급': '훌륭해요', '알찬': '훌륭해요', '쵝오': '훌륭해요', '훌륭':'훌륭해요', '감탄': '훌륭해요', '박수': '훌륭해요', '완벽': '훌륭해요', '황홀': '훌륭해요',
        '강추': '강추', '추천': '강추', 
        '신나게': '신나요',
        '눈물': '감동', '감동': '감동', 
        '어릴': '추억', '기억' : '추억', '추억': '추억', 
        '아이': '아이',
        '노래': '노래', 
        '춤': '춤',
        '선녀': '선녀님', '선녀님': '선녀님',
        '또': '재관람',
    }

    now_dict.update(new_dict)


    # 사전 전처리 결과값 확인
    result = []
    for word in DA_list:
        result += [now_dict[word] if now_dict.get(word) else word]
        
    result = Counter(result)
    result = list(result.most_common(200))
    result = list(filter(lambda x : x[1] > 300, result))


    show = pd.read_sql("SELECT * FROM performance;", connection)
    total_show_cnt = len(show.index)

    tag_map = {}

    for id in range(1, total_show_cnt + 1):
        review_data = pd.read_sql(f"SELECT * FROM review WHERE show_id = {id};", connection)
        for i in review_data.index:
            s = re.sub("[^ㄱ-ㅎㅏ-ㅣ가-힣 ]", "", str(review_data['contents'][i]))
            review_data.loc[i, 'contents'] = s
        
        review_words = []
        for k in review_data.index:
            review = str(review_data['contents'][k])
            if review:
                temp = mecab.pos(review)
                review_words += temp

        pretag_list = [word[0] for word in review_words if word[0] not in stopwords]

        tag = []
        for word in pretag_list:
            if now_dict.get(word):
                tag += [now_dict[word]]
            else:
                continue

        tag_result = Counter(tag)
        tag_result = list(tag_result.most_common(15))
        tag_result = [elem for elem in tag_result]
        tag_map[id] = tag_result


    # tag_map

    columns = ['show_id', 'tag_content', 'weight']
    DB_tag_data = []

    for show_id in tag_map.keys():
        for tag_elem in (tag_map[show_id] if tag_map[show_id] else [(None, None)]):        
            tag = tag_elem[0]
            weight = tag_elem[1]
            data = [show_id, tag, weight]
            DB_tag_data.append(data)


    DB_tag_result = pd.DataFrame(DB_tag_data, columns=columns)

    # 결측치 행 추출 및 제거
    DB_tag_result = DB_tag_result.dropna(axis=0)
    print(DB_tag_result)

    # DB 저장
    DB_tag_result.to_sql(name='show_tag', con=connection, if_exists='append', index=False)

    # DB 연결 끊기
    # connection.commit() : MySQL은 commit을 해줘야 하는데, SQLAlchemy 가 auto commit 을 해주기 때문에 따로 할 필요가 없다.
    connection.close()

    print("show_tag saved all")