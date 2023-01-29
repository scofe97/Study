def check():
    global result

    for n in range(9):
        for m in range(9):
            num = matrix[n][m]

            width = matrix[n]
            height = matrix_reverse[m]

            n2 = n// 3 * 3
            m2 = m// 3 * 3

            position = []
            for i in range(3):
                position.extend(matrix[n2+i][m2:m2+3])

            if width.count(num) != 1 or height.count(num) != 1 or position.count(num) != 1:
                result = 0
                return

for tnt in range(1, int(input())+1):
    matrix = [list(map(int, input().split())) for _ in range(9)]
    matrix_reverse = list(map(list, zip(*matrix)))
    result = 1
    check()


    print(f'#{tnt} {result}')


# 16:31


