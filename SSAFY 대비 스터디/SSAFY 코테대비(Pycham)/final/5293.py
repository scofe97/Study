T = int(input())

for tc in range(1,T+1):
    ans='impossible'

    # 00 01 10 11 카운트
    a,b,c,d=map(int,input().split())


    # 01이 10보다 하나 더 적을 때
    # 10110
    if b==(c+1):
        ans='0'*(a+1)+'1'*(d+1)+'01'* c



    # 01 10이 같고 01, 10중 무조건 하나 이상 있을 때, (00과11이 붙는과정에서 01이 생김)
    elif b==c and b!=0:
        ans='0'*(a+1)+'1'*(d+1)+'01'*(c-1)+'0'



    # 10이 01보다 하나 더 적을 때
    elif c==(b+1):
        ans='1'*(d+1)+'0'*(a+1)+'10'*b



    # 00 11중 하나라도 아무것도 없다면
    elif a*d==0:
        if a:
            ans='0'*(a+1)
        else:
            ans='1'*(d+1)


    print('#{} {}'.format(tc, ans))