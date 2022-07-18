from dataclasses import dataclass 

@dataclass
class novel_episodes:
    episode_number : int
    text_number : int
    episode_views : int = 0
    episode_rate : int = 0
    
    def __gt__(self, other):
        return self.episode_number > other.episode_number
    
    def __lt__(self, other):
        return self.episode_number < other.episode_number