from bs4 import BeautifulSoup

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import pandas as pd
# from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import time

# Crawling Setup
chromedriver = 'C:/Users/Chiwoo/Desktop/data/chromedriver'
browser = webdriver.Chrome(chromedriver)
browser.implicitly_wait(3)

url = 'https://mobileticket.interpark.com/goods/22000970?app_tapbar_state==fix#GoodsTabArea'
browser.get(url)

# 홈페이지 들어간 후 공지제거
try:
    browser.find_element_by_class_name(
        "buttonLayerCloseToday").send_keys(Keys.ENTER)
except:
    print("notice doesn`t exist")

time.sleep(0.1)

html = browser.page_source
soup = BeautifulSoup(html, 'html.parser')

# 공연이름
show_name = soup.find("div", {"class": "productsDefinitions"}).find(
    "h2").get_text()

# 카테고리
category = soup.find(
    "ul", {"class": "productsCondition"}).find("li").get_text()

# 포스터 경로
poster_path = soup.find("img", {"id": "mainPostImg"}).get("src")

# 공연날짜[시작, 종료]
show_date = soup.find("li", {"id": "productsDateText"}).find(
    "dd").get_text().split('~')
start_date = show_date[0]
end_date = ''
openrun = ''
if show_date[1] == '오픈런':
    openrun = show_date[1]
else:
    end_date = show_date[1]

# 공연장소, 공연장 상세주소, 공연장 전화번호, 공연장 홈페이지
try:
    art_center_name = soup.find(
        "div", {"class": "productsPlacePartner"}).find("dt").get_text()
    art_center_address = soup.find(
        "div", {"class": "productsPlacePartner"}).find("address").get_text()
    art_center_tel = soup.find("div", {"class": "productsPlacePartner"}).find_all(
        "li")[0].get_text()
    art_center_web = soup.find(
        "li", {"class": "productsLinkHomepage"}).find("a").get("data-url")
except:
    pass

# 출연진 리스트
casting_list = []
try:
    casting = soup.find_all("span", {"class": "productsCastName"})
    for idx in range(len(casting)):
        casting_list.append(casting[idx].get_text())
        print(casting[idx].get_text())
except:
    pass

# 관람일시
show_day = ''
try:
    show_day = soup.find(
        "div", {"class": "productsScheduleText"}).find("div").get_text()
except:
    pass

# 공지사항 이미지
notice_img_list = []
try:
    notice_img = soup.find(
        "div", {"class": "productsNoticeText"}).find_all("img")
    for idx in range(len(notice_img)):
        notice_img_list.append(notice_img[idx].get("src"))
except:
    print("noitcImg does not exist")

# 공연 상세 정보 팸플릿 이미지
show_detail_img = soup.find(
    "div", {"class": "productsDetailText"}).find("div", {"class": "productsImageWrap"}).find("img").get("src")

# 가격
price_list = []
seat_grade = soup.find_all("span", {"class": "productsSeatGrade"})
seat_price = soup.find_all("span", {"class": "productsSeatPrice"})
for idx in range(len(seat_grade)):
    temp = '{}: {}\n'.format(
        seat_grade[idx].get_text(), seat_price[idx].get_text())
    price_list.append(temp)
price = ''.join(price_list)


# 부가정보 버튼 클릭
browser.find_element_by_class_name("productsTabAdditional").click()
time.sleep(3)

# 크롤링 시작
html = browser.page_source
soup = BeautifulSoup(html, 'html.parser')

# 부가정보 크롤링
tableList = soup.find("ul", {"class": "tableList"}).find_all("span")
producer = tableList[1].get_text()   # 주최/기획
runtime = tableList[3].get_text()   # 공연시간
age = tableList[5].get_text()   # 관람등급

# 예매율 데이터
men_rate = soup.find("div", {"id": "manCustomer"}).get_text()
women_rate = soup.find("div", {"id": "womanCustomer"}).get_text()
age_rate = []
for i in range(1, 6):
    temp_age = soup.find(
        "li", {"class": "rank{}".format(i)}).find_all("div")[-1].get_text()
    temp_percent = soup.find("li", {"class": "rank{}".format(i)}).find(
        "div", {"class": "percent"}).find("div").get_text()
    age_rate.append('{} {}'.format(temp_age, temp_percent))

# 공연이름, 카테고리, 공연시작일, 공연종료일, 오픈런여부, 관람등급,
# 출연진정보, 상연시간, 제작사, 가격, 포스터,팸플릿 포스터, 공지사항 포스터, 관람일시, 공연장 이름, 남자예매율, 여자예매율, 나이대 예매율 리스트
show_data = [show_name, category, start_date, end_date, openrun, age,
             casting_list, runtime, producer, price, poster_path, show_detail_img, notice_img_list, show_day, art_center_name, men_rate, women_rate, age_rate]

print(show_data)
# 공연장 이름, 공연장 주소, 공연장 전화번호, 공연장 홈페이지
art_center_data = [art_center_name,
                   art_center_address, art_center_tel, art_center_web]
print(art_center_data)
# print(show_data)
data = []
data.append(show_data)
browser.close()
# df = pd.DataFrame(data, columns=['show_name', 'category', 'start_date', 'end_date', 'openrun', 'age'
#                                  'casting_list', 'runtime', 'producer', 'price', 'poster_path', 'art_center_name']).replace('\n', ' ', regex=True)

# print(df)
# 관람등급, 팸플릿 포스터/ 공연장 주소, 공지사항 그림(리스트), 남, 여, 10, 20, 30, 40, 50 예매비율
