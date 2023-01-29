# https://juun42.tistory.com/14

# n 1. 1번째 방법
a = list()
for i in range(10):
    tmp = []
    for j in range(5):
        tmp.append(j)
    a.append(tmp)
print(a)


# n 2. 2번째 방법
b = [[0] * 3] * 3
print(b) # [[0, 0, 0], [0, 0, 0], [0, 0, 0]]

# ? 이 방식은 리스트를 초기화할 때 모든 행이 같은 객체로 인식됨
b[1][1] = 1
print(b) # [[0, 1, 0], [0, 1, 0], [0, 1, 0]]


# n 3. 3번째 방법
c = [[0]*3 for _ in range(3)]
print(c) # [[0, 0, 0], [0, 0, 0], [0, 0, 0]]

c[1][1] = 1
print(c) # [[0, 0, 0], [0, 1, 0], [0, 0, 0]]