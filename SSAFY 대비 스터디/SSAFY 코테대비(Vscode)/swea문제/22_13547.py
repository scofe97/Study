def fight(count):
    
    ox = input()
    
    win = 0
    lose = 0
    for win_lose in ox:
        if win_lose == 'o':
            win += 1
        elif win_lose == 'x':
            lose += 1
            
        if lose > 7:
            print(f'#{count} NO')
            return
    
    print(f'#{count} YES')
        


count = int(input())
for i in range(count):
    fight(i+1)