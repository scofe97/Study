def dfs(day, pay):
    global max_pay

    if day > max_day:
        return

    if day == max_day:
        max_pay = max(max_pay, pay)
        return

    dfs(day + 1, pay)
    dfs(day + arr[day][0], pay + arr[day][1])


max_day = int(input())
max_pay = 0
arr = [list(map(int,input().split())) for _ in range(max_day)]
dfs(0, 0)

print(max_pay)


