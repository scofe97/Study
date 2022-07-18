from numpy import empty
from openpyxl import workbook
from openpyxl import load_workbook
from openpyxl.drawing.image import Image
from openpyxl.utils import get_column_letter
from 단계1_데이터_추출 import extract
from 단계1_데이터_추출2 import extract2
import string
from scipy.stats import rankdata


def tag_novel(tagName,filename):
    
    novels = extract2(filename)
    tag_Novels = []

    for index in range(0, len(novels)):
    
        if tagName in novels[index].tag:
            tag_Novels.append(novels[index])
    
    rankData_views = []
    # rankData_likes = []
    # rankData_bookmarks = []

    for item in tag_Novels:
        rankData_views.append(item.views)
        # rankData_likes.append(item.likes)
        # rankData_bookmarks.append(item.likes)

    rankData_views = rankdata(rankData_views)
    # rankData_likes = rankdata(rankData_likes)
    # rankData_bookmarks = rankdata(rankData_bookmarks)   

    for index in range(len(tag_Novels)):
        tag_Novels[index].rank = int(len(tag_Novels)-rankData_views[index]+1)
    
    
    return tag_Novels



