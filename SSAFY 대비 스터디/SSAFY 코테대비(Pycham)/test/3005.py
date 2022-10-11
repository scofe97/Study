n, m = map(int, input().split())
arr = []

result = -1

for i in range(n):
    arr.append(input())


for w in range(n):
    s = list(arr[w].replace('#', ' ').split())

    for s2 in s:
        if result == -1:
            if len(s2) > 1:
                result = s2
        elif result > s2 and len(s2) > 1:
            result = s2

for h in range(m):
    c = ''

    for j in range(n):
        c += arr[j][h]

    c = list(c.replace('#', ' ').split())

    for s2 in c:
        if result > s2 and len(s2) > 1:
            result = s2



print(result)

