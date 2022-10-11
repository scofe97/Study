result = []

def egcd(a, b):
    x,y, u,v = 0,1, 1,0
    while a != 0:
        q, r = b//a, b%a
        m, n = x-u*q, y-v*q
        b,a, x,y, u,v = a,r, u,v, m,n
    gcd = b
    return gcd, x, y


for tnt in range(1, int(input())+1):
    n1, n2 = map(int, input().split())

    e = egcd(n1,n2)

    if e[0] != 1:
        result.append(f'#{tnt} -1')
    else:
        result.append(f'#{tnt} {e[1]} {e[2]}')

for res in result:
    print(res)




