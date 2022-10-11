for _ in range(1, int(input())+1):
    
    # * 입력값을 받음
    step, size = input().split()
    size = int(size)
    arr = list(input().split())
    
    # * 문자 -> 숫자로 딕셔너리 ( 숫자로 정렬하기위해서 만듬 )
    dic_char = {"ZRO" : 0,"ONE" : 1,"TWO" : 2,"THR" : 3,"FOR" : 4,"FIV" : 5,"SIX" : 6,"SVN" : 7,"EGT" : 8,"NIN" : 9, }
    
    # * 숫자 -> 문자로 딕셔너리 ( 복구하기 위해서 만듬 )
    dic_int = {v:k for k,v in dic_char.items()}
    
    # * 모든 문자요소를 숫자로 치환
    for i in range(size):
        arr[i] = dic_char[arr[i]]
    
    # * 정렬    
    arr.sort()

    # * 다시 문자요소로 바꿈
    for i in range(size):
        arr[i] = dic_int[arr[i]]
            
    print(step)
    print(*arr)