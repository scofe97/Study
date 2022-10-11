for tnt in range(1, int(input())+1):
    s1, s2 = map(float,input().split())

    # 2번바꾸는 경우는 단 1가지이다
    # 처음에 올바르게 들고있다가 시도했는데 안꼽혀서 뒤집고 다시 뒤집어서 꼽는데 성공
    s1_success2 = s1 * (1-s2) * s2

    # 1번 바꾸는 경우도 1가지이다
    # 처음에 반대로 들고있다가 시도해서 안되서 뒤집고 꼽는데 성공
    s1_success1 = (1-s1) * s2

    if s1_success2 > s1_success1:
        print(f'#{tnt} YES')
    else:
        print(f'#{tnt} NO')
