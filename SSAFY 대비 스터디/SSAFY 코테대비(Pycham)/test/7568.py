arr = []
size = int(input())
for i in range(size):
    arr.append(list(map(int, input().split())))

# 덩치는 키 몸무게가 큰사람을 의미
# 둘의 값이 다르면 비교 불가

result = []

# 1 2 3 4 5
for i in range(size):
    step = size

    for j in range(size):
        if i != j and (arr[i][1] >= arr[j][1] or arr[i][0] >= arr[j][0]):
            step -= 1

    result.append(step)

print(*result)