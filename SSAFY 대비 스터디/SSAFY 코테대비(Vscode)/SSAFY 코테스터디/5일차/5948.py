
# * 3개 길이의 모든 순열의 경우를 뽑아서 합을 모두 뽑음
# * 중복을 방지하기위해 set을 사용
def problem_5948(cnt, idx, result):
    if cnt == 3:
        sum_arr.add(result)
        return
    
    for i in range(idx, len(arr)):
        problem_5948(cnt+1, i+1, result + arr[i])

for tnt in range(1, int(input())+1):
    
    arr = list(map(int, input().split()))
    sum_arr = set()
    problem_5948(0, 0, 0)
    
    # * 크기순으로 정렬하고 뒤에서 5번째 출력
    sum_arr = list(sum_arr)
    sum_arr.sort()
    
    print(f'#{tnt} {sum_arr[-5]}')
    
    