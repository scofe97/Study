for tnt in range(1, int(input())+1):
    d, h, m = map(int, input().split())
    result = (24 * 60 * (d-11)) + (60 * (h-11)) + (m-11)
    
    if result >= 0:
        print(f'#{tnt} {result}')
    else:
        print(f'#{tnt} -1')
    
    