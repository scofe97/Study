import pyautogui
import pyperclip
import clipboard
from time import sleep
import re
import Data_novel


Novel_size = 50
Novels = []


def page_information():
    sleep(1)
    pyautogui.moveTo(560, 230)
    sleep(0.2)
    pyautogui.click(clicks=5)
    pyautogui.hotkey('ctrl', 'c')
    title = clipboard.paste()
    
    sleep(0.2)
    pyautogui.moveTo(560, 260)
    pyautogui.click(clicks=5)
    pyautogui.hotkey('ctrl', 'c')
    information1 = clipboard.paste()
    sleep(0.2)
    print(information1)
    author = information1.split('  ')[1]
    
    novel = Data_novel.Novel(title, author)
    pyautogui.moveTo(570, 330)
    pyautogui.click(clicks=5)
    pyautogui.hotkey('ctrl', 'c')
    information2 = clipboard.paste()
    sleep(0.2)
    string = information2.split()

    for item in string:

        is_views = re.search('명$', item)
        is_episoides = re.search('회차$', item)
        is_likes = re.search('회$', item)

        if(is_views != None):
            novel.views = int(item[:len(item)-1].replace(',', ''))

        if(is_episoides != None):
            novel.episodes = int(item[:len(item)-2].replace(',', ''))

        if(is_likes != None):
            novel.likes = int(item[:len(item)-1].replace(',', ''))


    pyautogui.moveTo(1480, 260)
    pyautogui.click(clicks=5)
    sleep(0.2)
    pyautogui.hotkey('ctrl', 'c')
    novel.bookmarks = int(clipboard.paste().replace('\t',""))

    print(novel)

    return novel


def page_row_move():
    print("스크롤")
    if(len(Novels) == Novel_size):
        return
    sleep(0.4)
    pyautogui.scroll(-325)


def page_column_move():
    sleep(0.3)
    for x in range(6):
        
        if(len(Novels) == Novel_size):
            return
        if(x == 0):
            pyautogui.moveTo(450, 600)
            pyautogui.click()
            Novels.append(page_information())
            pyautogui.hotkey('alt', 'left')
            sleep(0.5)
        else:
            pyautogui.moveTo(450, 600)
            pyautogui.moveRel(200*x, 0,)
            pyautogui.click()
            Novels.append(page_information()) 
            pyautogui.hotkey('alt', 'left')
            sleep(0.5)


def novel_information():
    pyautogui.moveTo(100, 100)
    pyautogui.click()
    while len(Novels) != Novel_size:
        page_column_move()
        page_row_move()

    return Novels


