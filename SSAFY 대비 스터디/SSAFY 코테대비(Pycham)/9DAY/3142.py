for tnt in range(1, int(input())+1):
    horn, animal = map(int, input().split())

    one = two = 0

    for i in range(animal+1):
        for j in range(animal-i+1):

            # i가 뿔이 2개인 동물, j가 1개인 동물
            if i+j == animal and i*2 + j == horn:
                two = i
                one = j

    print(f'#{tnt} {one} {two}')