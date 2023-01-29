from dataclasses import dataclass, field
from typing import List
from 소설_회차 import *

@dataclass
class Novel: 
    title : str
    b_19 : str
    thumbnail : str
    author : str
    views : int
    episodes: int
    likes: int
    introduction : str
    tag : List[str]
    episode_start_30 : List[novel_episodes] = field(default_factory=list)
    episode_end_30 : List[novel_episodes] = field(default_factory=list)
    rank : int = -1

    def __gt__(self, other):
        return self.rank > other.rank
    
    def __eq__(self, other):
        return self.title == other.title or (self.author == other.author and self.introduction == other.introduction and (self.introduction != "" and other.introduction != ""))
    
    def __lt__(self, other):
        return self.rank < other.rank