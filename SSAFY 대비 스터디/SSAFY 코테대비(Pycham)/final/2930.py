import heapq

for tnt in range(1, int(input())+1):

    heap = []
    N = int(input())
    print(f'#{tnt}' , end=' ')

    for _ in range(N):
        order = list(input().split())

        if order[0] == '1':
            num = int(order[1])
            # (우선순위, 값)
            heapq.heappush(heap, (-num, num))
        else:
            if heap:
                print(heapq.heappop(heap)[1], end=' ')
            else:
                print('-1', end=' ')