from bs4 import BeautifulSoup

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import pandas as pd
# from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

import time


# Musical = ["http://mticket.interpark.com/genre?Genre=MU&cat=11007","http://mticket.interpark.com/genre?Genre=MU&cat=11008","http://mticket.interpark.com/genre?Genre=MU&cat=11009","http://mticket.interpark.com/genre?Genre=MU&cat=11010"]#오리지널/내한, 라이선스, 창작 뮤지컬, 넌버벌 퍼포먼스

### Crawling Setup
def get_urls_list(url):
    urls_list = []
    chromedriver = 'C:/Users/multicampus/Downloads/chromedriver_win32/chromedriver'
    browser = webdriver.Chrome(chromedriver)
    browser.implicitly_wait(3)

    # url = 'http://mticket.interpark.com/genre?Genre=MU&cat=11008'
    # url = "http://mticket.interpark.com/genre?Genre=MU";
    browser.get(url)

    while 1:
        try:
            browser.find_element_by_class_name("moreBtn").send_keys(Keys.ENTER)
            time.sleep(0.1)
        except:
            break

    html = browser.page_source
    soup = BeautifulSoup(html, 'html.parser')

    # score 크롤링 userBoardList
    urls = soup.find("div", {"class": "swiper-slide swiper-slide-active"})
    urls = urls.find("ul", {"class": "allListWrap"})
    for url in urls.find_all("li"):
        urls_list.append("https:" + url.find("a")["href"])

    browser.close()
    return urls_list

print(get_urls_list("http://mticket.interpark.com/genre?Genre=MU"))