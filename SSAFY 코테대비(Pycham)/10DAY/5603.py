for tnt in range(1, int(input())+1):
    n = int(input())
    arr = []

    for _ in range(n):
        arr.append(int(input()))

    # 모든 건초의 길이가 같아진다는 뜻은, 현재 건초의 총합의 평균값으로 모두 바뀐다는 것이다.
    arr_avg = sum(arr)/n
    result = 0

    # 그리고 각 건초값과 평균값의 차이를 모두 더하고 나누기 2를하면 몇번 바꿔야하는지 구해진다.
    for i in range(n):
        result += abs(arr[i] - arr_avg)

    print(f'#{tnt} {int(result/2)}')