for _ in range(10):
    tnt = int(input())
    
    # * 찾을 단어
    search = input()
    # * 전체문장
    sentence = input()
    # * 몇번 나오는가
    result = 0
    
    # * 해당 단어가 있을때까지 반복함
    while sentence.find(search) != -1:
        
        result += 1
        
        # * 있다면 단어가 나온 곳까지 짜르고 다시 반복
        idx = sentence.find(search)
        sentence = sentence[idx+len(search):]
    
    print(f'#{tnt} {result}')