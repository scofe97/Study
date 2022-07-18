for tnt in range(1, int(input())+1):
    p, q, r, s, w = map(int, input().split())

    pay_a = w*p
    pay_b = q

    if w - r > 0:
        pay_b += (w-r)*s


    result = min(pay_a, pay_b)
    print(f'#{tnt} {result}')


# 06:33