for tnt in range(1, int(input())+1):
    n, m = map(int, input().split())

    # m을 2진수로 변환한뒤, 문자열로 바꾸고 뒤집어줌
    m = str(bin(m))[2:][::-1]
    result_arr = 0

    # n 만큼의 1이 반복되는가?
    for s in m:
        if s == '1':
            result_arr += 1
        else:
            break

    # 반복되면 on, 아니면 off
    if result_arr >= n:
        print(f'#{tnt} ON')
    else:
        print(f'#{tnt} OFF')

