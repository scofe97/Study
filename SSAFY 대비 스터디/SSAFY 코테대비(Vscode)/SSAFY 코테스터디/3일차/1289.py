def problem_1289():
    global result
    
    for i in range(len(arr)):
        
        # * 만약 i번째 위치가 서로 다르면 바꿔준다.
        if arr[i] != arr2[i]:
            result += 1
            change = arr[i]
            
            # * i ~ 배열길이 만큼 바꿔줌
            for j in range(i, len(arr)):
                arr2[j] = change
    

for tnt in range(1, int(input()) +1):
    arr = list(input())
    arr2 = ['0'] * len(arr)
    result = 0
    problem_1289()
    print(f'#{tnt} {result}')