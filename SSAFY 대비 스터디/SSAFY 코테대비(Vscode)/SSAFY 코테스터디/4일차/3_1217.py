
# * 단순한 제곱문제를 재귀를 사용해서 푸는거라 크게 풀이가 없음
def problem_1217(cnt, r):
    if cnt == m:
        return r
    
    return problem_1217(cnt+1, r * n)

for _ in range(10):
    tnt = int(input())
    n,m = map(int, input().split())
    
    print(f'#{tnt} {problem_1217(0,1)}')