for tnt in range(1, int(input())+1):
    
    # * 문자열을 리스트로 받음
    s = list(input())
    
    # * 쌍을 제거하며 받을 스택
    stack = []
    
    # * 이거 때문에 시간이 조금 더 걸렸는데 정렬기준으로도 쌍이 없어야하므로 미리 정렬해두고 한다.
    s.sort()
    
    
    while s:
        
        # * 요소를 pop한다
        stack.append(s.pop())
        
        # * 그런데 앞에서 뺀 문자와, s마지막이 같으면
        while s and stack and stack[-1] == s[-1]:
            
            # * stack 과 s 모두 pop한다
            stack.pop()
            s.pop()
    
    # * 순서대로 넣어야하므로 다시 한번 정렬
    stack.sort()
    
    if stack: 
        result = ''.join(stack)
        print(f'#{tnt} {result}')
    else:
        print(f'#{tnt} Good')