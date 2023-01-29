for tnt in range(1, int(input())+1):

    memory1 = list(input())
    length = len(memory1)
    result = 0
    memory2 = ['0'] * length

    for i in range(length):
        if memory1[i] != memory2[i]:

            if memory1[i] == '1':
                result += 1
                for j in range(i, length):
                    memory2[j] = '1'
                    
            elif memory1[i] == '0':
                result += 1
                for j in range(i, length):
                    memory2[j] = '0'

    print(f'#{tnt} {result}')