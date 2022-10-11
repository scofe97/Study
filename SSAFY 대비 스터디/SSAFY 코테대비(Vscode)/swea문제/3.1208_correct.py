T = 10
for tc in range(1, T +1):
    N = int(input())
    numbers = list(map(int, input().split()))
    for i in range(N):
        max_num = max(numbers)
        min_num = min(numbers)
        index_max_num = numbers.index(max_num)
        index_min_num = numbers.index(min_num)
        numbers[index_max_num] -= 1
        numbers[index_min_num] += 1
    print('#{} {}'.format(tc, max(numbers)-min(numbers)))
