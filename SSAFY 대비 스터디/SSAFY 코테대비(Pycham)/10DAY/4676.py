for tnt in range(1, int(input())+1):
    s = list(input())
    cnt = int(input())
    point = list(map(int,input().split()))

    # 뒤에서부터 넣어야 넣어야하는 idx 변화가 안생김
    point.sort(reverse = True)

    for i in range(cnt):
        s.insert(point[i], '-')

    result = ''.join(s)

    print(f'#{tnt} {result}')