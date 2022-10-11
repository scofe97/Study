for tnt in range(1, int(input())):
    size = int(input())

    matrix = [list(input().split()) for _ in range(size)]

    matrix_90 = list(map(list, zip(*matrix[::-1])))
    matrix_180 = list(map(list, zip(*matrix_90[::-1])))
    matrix_270 = list(map(list, zip(*matrix_180[::-1])))

    print(f'#{tnt}')


    for i in range(size):
        mat_90 = ''.join(matrix_90[i])
        mat_180 = ''.join(matrix_180[i])
        mat_270 = ''.join(matrix_270[i])

        print(f'{mat_90} {mat_180} {mat_270}')