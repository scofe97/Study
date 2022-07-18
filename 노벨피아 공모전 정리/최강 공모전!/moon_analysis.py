import selenium
from selenium import webdriver
from selenium.webdriver import ActionChains

from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By

from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait



# 불러오기 (드라이버 & 웹 페이지창 열기)
URL = 'https://novel.munpia.com/261064'

driver = webdriver.Chrome(executable_path='chromedriver')
driver.get(url=URL)



# 현재 url 얻기
# print(driver.current_url)

# 브라우저 닫기
# driver.close()


# 로딩대기
# 브라우저에서 해당 웹 페이지의 요소들을 로드하는 데 시간이 걸림
# 따라서 element가 존재하지 않는다는 error를 보지않으려면 대기해야함

# 암묵적대기 : 로드될때 까지 지정시간만큼 기다림 (예시는 5초까지 기다려줌)


# 명시적 대기 : 무조건 초까지 기다림 (효율이 안좋으니 지양하자)

Novels = []




for click in range(2):
    driver.implicitly_wait(time_to_wait=5)
    
    episodes = driver.find_elements_by_css_selector('#ENTRIES > tbody > tr:nth-child(n+13) > td.index > span')

    title = driver.find_elements_by_css_selector('#ENTRIES > tbody > tr:nth-child(n+13) > td.subject > a:nth-child(6)')

    comments = driver.find_elements_by_css_selector('#ENTRIES > tbody > tr:nth-child(n+13)  > td.subject > a.comment.trigger-window')

    upload_date = driver.find_elements_by_css_selector('#ENTRIES > tbody > tr:nth-child(n+13)  > td.date')

    prices = driver.find_elements_by_css_selector('#ENTRIES > tbody > tr:nth-child(n+13) > td:nth-child(5)')
    
    amount = driver.find_elements_by_css_selector('#ENTRIES > tbody > tr:nth-child(n+13) > td:nth-child(7)')

    next_page = driver.find_element_by_xpath('//*[@id="board"]/div[8]/ul/li[4]/a')
    
    print('+'*100)
    for i in range(len(episodes)):
        novel = []
        novel.append(episodes[i].text)
        novel.append(title[i].text)
        novel.append(comments[i].text)
        novel.append(upload_date[i].text)
        novel.append(prices[i].text)
        novel.append(amount[i].text)
        print(novel)
        print()
    print('-'*100)
    
    next_page.click()

driver.close()