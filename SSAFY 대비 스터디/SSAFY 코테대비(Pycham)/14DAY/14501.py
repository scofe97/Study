def dfs(idx, s):
    global result

    if day == idx:
        result = max(result, s)
        return
    if day < idx:
        return

    dfs(idx + arr[idx][0] , s + arr[idx][1])
    dfs(idx + 1 , s)


day = int(input())
arr = []
dp = [0] * day
for i in range(day):
    arr.append(list(map(int, input().split())))

result = 0
dfs(0,0)

print(result)