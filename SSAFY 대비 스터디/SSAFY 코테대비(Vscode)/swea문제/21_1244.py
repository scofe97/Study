def dfs(idx, cnt):
    
    global answer
    
    # 만약 교체회수가 다되었다면
    if cnt == int(target):
        answer = max(int("".join(nums)), answer)
        return
    
    for now in range(idx, n): # 우리는 0 idx부터 가장 큰 값의 숫자를 넣어줄거임
        for max_idx in range(now + 1 ,n): # 숫자비교 (가장 큰 숫자 찾는과정) 
            
            if nums[now] <= nums[max_idx]: # 만약 큰숫자가 발견된다
                nums[now], nums[max_idx] = nums[max_idx], nums[now]
                dfs(now, cnt + 1) # dfs로 넘겨줌
                nums[now], nums[max_idx] = nums[max_idx], nums[now] # dfs이므로 다시 원위치
    if not answer and cnt < int(target): # 만약 변경횟수가 숫자 크기보다 큰 경우
        
        rotate = (int(target) - cnt) % 2
        
        if rotate: # 남은 횟수가 짝수인가?
            nums[-1], nums[-2] = nums[-2], nums[-1] # 홀수이면 끝자리 2개를 변경
        dfs(idx, int(target)) # 변경횟수 모두소진
                
                
for test in range(1, int(input()) + 1):
    
    nums, target = input().split()
    n = len(nums)
    nums = list(nums)
    answer = 0
    dfs(0,0)
    print(f'#{test} {answer}')