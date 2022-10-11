for tnt in range(1, int(input())+1):
    edges = list(map(int, input().split()))
    
    if edges.count(edges[0]) == 1:
        print(f'#{tnt} {edges[0]}')
    elif edges.count(edges[1]) == 1:
        print(f'#{tnt} {edges[1]}')
    else :
        print(f'#{tnt} {edges[2]}')