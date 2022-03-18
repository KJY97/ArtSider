from bs4 import BeautifulSoup

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import pandas as pd
import GetCategory as ca
# from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
# from selenium.webdriver.chrome.service import Service
# from webdriver_manager.chrome import ChromeDriverManager

import time
ca.get_urls_list()
### Crawling Setup
chromedriver = 'C:/Users/multicampus/Downloads/chromedriver_win32/chromedriver'
browser = webdriver.Chrome(chromedriver)
browser.implicitly_wait(3)
review_list = []
url = 'https://mticket.interpark.com/Goods/GoodsInfo/info?GoodsCode=22000028&app_tapbar_state=fix'
browser.get(url)

# 인터파크 처음 모달창 없애기(있을 경우에만 실행)
try:
    browser.find_element_by_class_name("buttonLayerCloseToday").send_keys(Keys.ENTER)
    time.sleep(0.5)
except:
    print("no modal")

# 인터파크 리뷰보기 클릭
try:
    browser.find_element_by_class_name("productsTabPosts").click()
except:
    print("review tab doesn't exist")

# 인터파크 리뷰 더보기 버튼 끝까지 클릭(리뷰 정보를 전체다 띄우기 위해서)
while 1:
     try:
        browser.find_element_by_class_name("moreBtn").send_keys(Keys.ENTER)
        time.sleep(0.1)
     except:
        break

try:


    # 크롤링 시작
    html = browser.page_source
    soup = BeautifulSoup(html, 'html.parser')

    # score 크롤링 userBoardList
    reviews = soup.find("ul", {"id": "writerInfo"})
    reviews = reviews.find_all("li")

    # 리뷰 하나당 별점, 좋아요, 내용, user, 조회, 날짜, 예매자인지 여부

    for review in reviews:
        # 공연id 임의값으로 넣어 놨음 수정 필요
        id = 0
        # 별점
        score = review.find("div", {"class": "shareInfo"}).find('div').get('class')[1]
        score = score[5:]  # point10 형태에서 숫자만 나오도록 자르기
        # 좋아요
        like = review.find("span", {"class": "good"}).getText()
        # 내용
        content = review.find("div", {"class": "userBoardTitle"}).getText()

        plus_data = review.find("div", {"class": "userBoardWriterInfo"}).find_all("span")
        # user
        user = plus_data[0].getText()
        # 조회수
        views = plus_data[2].getText()[2:]
        # 등록날짜
        date = plus_data[3].getText()
        # 관람객 여부
        is_buyer = True
        if len(plus_data) != 5:
            is_buyer = False
        review_list.append([score, like, content, user, views, date, is_buyer])
except:
    print("no reviews")

browser.close()
total = len(review_list)
print(total)
df = pd.DataFrame(review_list, columns=['score', 'like', 'content', 'user', 'views', 'date', 'is_buyer'])
df = df.replace('\n', ' ', regex=True)
df.to_csv('reviews.csv')
