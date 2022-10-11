for tnt in range(1, int(input())+1):

    word1 = list(input().strip())
    word2 = list(input().strip())

    size = min(len(word1), len(word2))

    for i in range(size):

        word1_c = word1.pop(0)
        word2_c = word2.pop(0)

        if word1_c != word2_c:
            print(f'#{tnt} Y')
            break
    else:
        if len(word2) == 1 and word2[0] == 'a':
            print(f'#{tnt} N')
        else:
            print(f'#{tnt} Y')

