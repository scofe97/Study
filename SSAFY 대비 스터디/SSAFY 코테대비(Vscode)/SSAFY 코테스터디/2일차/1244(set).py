# 중복을 제외한 완전 탐색
# * 원본, 길이, 횟수
def dfs(set_num, lens, count):
    set_tmp = set()
    
    # set_num -> {'123'}1 
    print(set_num)
    for _ in range(count):   # 각 횟수의 경우수에서 다시 교환됬을때(count까지)모든 경우의수를 반복 
        while set_num:
            data = set_num.pop()
            list_tmp = list(data)   # idx교환이 용이하게 list로 바꿔주었다.
            for i in range(lens):
                for j in range(i+1, lens):
                    list_tmp[i],list_tmp[j] = list_tmp[j], list_tmp[i]
                    set_tmp.add(''.join(list_tmp))  # 교환된 숫자를 set에 넣는다.
                    list_tmp[i],list_tmp[j] = list_tmp[j], list_tmp[i]   # 다음 교환을 위해 원래 위치로 되돌린다.
        
        print("1",set_num, set_tmp)            
        set_num,set_tmp = set_tmp,set_num  
    
    return set_num


T = int(input())

for tc in range(1, T + 1):
    num, count = map(int, input().split())
    num = str(num)
    lens = len(num)
 
    
    set_num = set([num]) 
    set_tmp = set() # 중복제거를 위해 set자료구조를 이용
    
    set_num = dfs(set_num, lens, count)
    max_num = max(map(int, set_num))
    print('#{} {}'.format(tc, max_num))