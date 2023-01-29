for tnt in range(1, int(input())+1):

    arr = []
    max_length = 0
    for _ in range(5):
        s = list(input())
        # * 5단어중 가장 긴 단어만큼 반복하기에 최대길이를 구함
        max_length = max(max_length, len(s))
        arr.append(s)

    result_arr = []

    for i in range(max_length):
        for j in range(5):
            # * 만약 아직 단어가 남아있다면
            if arr[j]:
                result_arr.append(arr[j].pop(0))

    result_arr = ''.join(result_arr)
    print(f'#{tnt} {result_arr}')