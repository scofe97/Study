for tnt in range(1, int(input())+1):
    size = int(input())

    s = list(input())
    sta = 0
    sentence = []
    result = []

    for i in range(len(s)):
        if s[i] == '.' or s[i] == '!' or s[i] == '?':
            sentence.append(''.join(s[sta:i]).split())
            sta = i+1


    for i in sentence:
        step = 0
        for j in i:
            if ''.join(j[0]).isupper() and ''.join(j[1:]).islower() and ''.join(j[1:]).isalpha():
                step += 1
        result.append(step)

    print(f'#{tnt}', end=' ')
    for i in result:
        print(f'{i}', end=' ')
    print()



