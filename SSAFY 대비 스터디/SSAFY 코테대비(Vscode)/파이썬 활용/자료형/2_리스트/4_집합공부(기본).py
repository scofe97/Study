s1 = set(['12344445'])
print(s1) # {'12344445'}

while s1:
    data = s1.pop()
    print(data)
    list_tmp = list(data)
    print(list_tmp)
    
# ['1', '2', '3', '4', '4', '4', '4', '5']   

print('-'*10)



s1 = set('12344445')
print(s1) # {'4', '3', '5', '2', '1'}

while s1:
    data = s1.pop()
    print(data)
    list_tmp = list(data)
    print(list_tmp)
    

print('-'*10)

s1 = set([1,1,2,2,2,3,3,3,3,4,4,4,4,5])
print(s1) # {'4', '3', '5', '2', '1'}

while s1:
    data = s1.pop()
    print(data)
    list_tmp = [data]
    print(list_tmp)