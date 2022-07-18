from dataclasses import dataclass, field
from typing import List
from 소설_회차 import *

# * 2022/04/10부터 코드형식으로 뽑을것 ( 09일부터 코드형식으로 변경 )

@dataclass
class Novel: 
    code : int
    title : str
    b_19 : str
    thumbnail : str
    author : str
    views : int
    episodes: int
    bookmarks : int
    alarms : int
    likes: int
    introduction : str
    tag : List[str]
    episode_start_30 : List[novel_episodes] = field(default_factory=list)
    episode_end_30 : List[novel_episodes] = field(default_factory=list)
    rank : int = -1

    def __gt__(self, other):
        return self.rank > other.rank
    
    def __eq__(self, other):
        return self.code == other.code

    
    def __lt__(self, other):
        return self.rank < other.rank