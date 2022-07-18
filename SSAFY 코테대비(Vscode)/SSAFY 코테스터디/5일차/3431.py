for tnt in range(1, int(input())+1):
    l, u, x = map(int, input().split())
    
    # * 운동을 덜했으면 얼마나 덜했는지
    if x < l:
        print(f"#{tnt} {l-x}")
        
    # * 운동을 초과했으면 -1
    elif x > u:
        print(f"#{tnt} {-1}")
        
    # * 운동을 적절히 했으면 0
    else:
        print(f"#{tnt} {0}")