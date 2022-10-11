test = [[1,2,3],[4,5,6],[7,8,9]]

print(test[:2][1])
print(test[2][:2])


a = [0,1,2,3,4,5,6,7,8,9,10]
for i in range(6):
    for j in range(6):
        index = i+j
    print(f'index : {index} i : {i} j : {j}')
