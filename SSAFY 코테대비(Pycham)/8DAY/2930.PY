def op1(idx, number):
    if number == 0:
        return

    if idx == 0:
        if heap[1] < number:
            heap[1], number = number, heap[1]

        op1(1, number)

    else:
        if heap[idx*2] < number:
            idx = idx*2
            heap[idx], number = number, heap[idx]
            op1(idx, number)

        elif heap[idx*2+1] < number:
            idx = idx*2+1
            heap[idx], number = number, heap[idx]
            op1(idx, number)

        elif heap[idx*2] >= heap[idx*2+1]:
            idx = idx * 2
            op1(idx, number)

        else:
            idx = idx * 2 + 1
            op1(idx, number)


def op2(idx):
    if not heap[idx]:
        return -1

    root = heap[idx]
    heap[idx] = 0

    while heap[idx * 2] != 0 or heap[idx * 2 + 1] != 0:
        if heap[idx * 2] >= heap[idx * 2 + 1]:
            heap[idx], heap[idx * 2] = heap[idx * 2], heap[idx]
            idx = idx * 2

        else:
            heap[idx], heap[idx * 2 + 1] = heap[idx * 2 + 1], heap[idx]
            idx = idx * 2 + 1

    return root


for tnt in range(1, int(input()) + 1):
    cnt = int(input())
    heap = [0] * 100001
    result_arr = []

    for _ in range(cnt):
        op = list(map(int, input().split()))

        if op[0] == 1:
            op1(0, op[1])

        elif op[0] == 2:
            result_arr.append(op2(1))

    result_arr = ' '.join(list(map(str, result_arr)))
    print(f'#{tnt} {result_arr}')