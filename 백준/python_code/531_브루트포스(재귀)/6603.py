import sys
input = sys.stdin.readline

def dfs(s, depth):
    if depth==6:
        print(lst)
        return

    for i in range(s, k):
        lst.append(arr[i])
        dfs(i+1, depth+1)
        lst.pop()

while True:
    arr = list(map(int, input().split()))
    k = arr.pop(0)
    if k == 0:
        break

    lst = []
    dfs(0, 0)    # 시작 인덱스, lst의 길이
    print()
    
    
