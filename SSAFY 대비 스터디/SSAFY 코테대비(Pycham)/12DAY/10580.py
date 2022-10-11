
result = []

for tnt in range(1, int(input())+1):
    line = int(input())
    arr = []
    r = 0

    # p1,p2 를 리스트로받아서 arr 에 담음
    for _ in range(line):
        arr.append(list(map(int,input().split())))

    # p1을 기준으로 정렬한다
    arr.sort(key = lambda x : x[0])

    # i 이후의 선중 i보다 p2가 낮으면 모두 중첩된다.
    for i in range(line-1):
        for j in range(i+1, line):
            if arr[i][1] > arr[j][1]:
                r += 1

    result.append(f'#{tnt} {r}')

for i in result:
    print(i)


