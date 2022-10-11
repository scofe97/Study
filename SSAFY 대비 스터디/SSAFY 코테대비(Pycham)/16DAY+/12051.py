result = []

def gcd(a,b):
    if b == 0:
        return a
    else:
        return gcd(b, a%b)


for tnt in range(1, int(input())+1):
    n, pd, pg = map(int, input().split())


    if pg == 100 and pd != 100:
        result.append(f'#{tnt} Broken')

    elif pg == 0 and pd != 0:
        result.append(f'#{tnt} Broken')

    else:
        if 100 // gcd(100, pd) <= n:
            result.append(f'#{tnt} Possible')
        else:
            result.append(f'#{tnt} Broken')

for res in result:
    print(res)