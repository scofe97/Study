from dataclasses import dataclass 

@dataclass
class Novel: 
    title : str
    author : str
    plus : bool
    monopoly : bool
    views : int  = 0
    episodes: int  = 0
    likes: int = 0
    tag : str = ""
    
    def __gt__(self, other):
        return self.title > other.title
    
    def __eq__(self, other):
        return self.title == other.title
    
    def __lt__(self, other):
        return self.title < other.title