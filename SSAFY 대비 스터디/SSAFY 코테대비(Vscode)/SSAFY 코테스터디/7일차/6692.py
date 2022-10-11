for tnt in range(1, int(input())+1):
    n = int(input())
    result = 0
    
    for i in range(n):
        p, x = map(float, input().split())
        result += (p*x)
        
    print(f'#{tnt} {result:.6f}')