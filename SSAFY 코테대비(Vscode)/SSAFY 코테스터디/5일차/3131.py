arr  = [1] * 1000001
result = []

for i in range(2, 1000001):
    
    if arr[i]:
        result.append(i)
        for j in range(i+i,1000001, i):
            arr[j] = 0
            

print(*result)