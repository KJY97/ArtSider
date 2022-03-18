import requests
from bs4 import BeautifulSoup

api_url = 'http://www.kopis.or.kr/openApi/restful/pblprfr?service=1ac28adcb4b3407cb9e8a18355c8ae60&stdate=20220301&eddate=20220331&cpage=1&rows=40'

res = requests.get(api_url)

# XML도 Html과 같이 html parser를 이용한다.
bs = BeautifulSoup(res.content, 'html.parser')

# dbs 태그를 모두 불러와서 data 변수에 저장
data = bs.find_all('db')

show_list = []

# show_list에 id만 저장한다.
for db in data:
    show_id = db.find('mt20id')
    # show_name = db.find('prfnm')
    # print('{} : {}'.format(show_id.get_text(), show_name.get_text()))
    show_list.append(show_id.get_text())

# 공연상세정보를 조회하는 url에 id를 넣고 조회한다.
show_url = 'http://www.kopis.or.kr/openApi/restful/pblprfr/' + \
    show_list[0] + '?service=1ac28adcb4b3407cb9e8a18355c8ae60'

show_res = requests.get(show_url)

show_bs = BeautifulSoup(show_res.content, 'html.parser')

show_data = show_bs.find_all('db')

print(show_data)
