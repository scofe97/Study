
# * 몇번 바꾸어서 숫자 최대값 만들기
def problem_1244(idx, cnt):
    global result

    # * 바꾸는 횟수를 모두 채웠다면 그대로 출력
    if cnt == change:
        result = max(result, int("".join(n)))
        return

    # * 숫자의 최대값은 앞자리에 큰 숫자가 와야한다.
    # * now는 현재 자리수이며, max_idx에서 now 자리수에있는 숫자보다 큰 숫자를 찾고 있으면 바꾼다.
    for now in range(idx, len(n)):
        for max_idx in range(now + 1, len(n)):
            if n[now] <= n[max_idx]:
                n[now], n[max_idx] = n[max_idx], n[now]
                problem_1244(now, cnt + 1)
                n[now], n[max_idx] = n[max_idx], n[now]

    # * 만약 결과값이 아직 안나오고, 변화값이 남았다면 
    # * 최대값을 유지하기 위해선 끝의 2자리를 계속해서 바꿔줄거다
    if not result and change > cnt:
        
        # * 남은 숫자가 짝수가 아니라면 끝의 2자리를 변경해준다
        if (change-cnt) % 2:
            n[-1], n[-2] = n[-2], n[-1]
            
        problem_1244(idx, change)


for tnt in range(1, int(input())+1):

    n, change = input().split()
    n = list(n)
    change = int(change)
    result = 0
    problem_1244(0, 0)

    print(f'#{tnt} {result}')
