for tnt in range(1, int(input())+1):

    # 각 월의 날짜수
    month = {1: 31, 2 : 29,3: 31,4: 30,5: 31, 6: 30,7: 31,8: 31,9: 30,10: 31,11: 30,12: 31, }

    m, d = map(int, input().split())

    day = 0

    # 1월에서 m-1까지 계산
    for i in range(1, m):
        day += month[i]

    # m월의 날짜 d를 더해주고 7로 나눈 나머지를 뽑음
    day += d
    day %= 7

    # 기본값을 3으로 준이유는 해당 계산대로하면 작년 12/31 부터 시작하는 것이기 때문
    result = (3 + day) % 7

    print(f'#{tnt} {result}')