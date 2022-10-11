prime = [2]
for i in range(3, int(10000000 ** 0.5) , 2):
    for p in prime:
        if not i % p: break
    else:
        prime.append(i)
        
print(prime)
