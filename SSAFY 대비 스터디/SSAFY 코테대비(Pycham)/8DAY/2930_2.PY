for tnt in range(1, int(input()) + 1):
    cnt = int(input())
    heap = []
    result_arr = []

    for _ in range(cnt):
        op = list(map(int, input().split()))

        if op[0] == 1:
            heap.append(op[1])
            heap.sort()

        elif op[0] == 2:
            if heap:
                result_arr.append(heap.pop(0))
            else:
                result_arr.append(-1)

    result_arr = ' '.join(list(map(str, result_arr)))
    print(f'#{tnt} {result_arr}')