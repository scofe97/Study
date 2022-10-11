for tnt in range(1, int(input()) + 1):
    cards = list(input())

    # 카드번호 1~13 , 무늬별로 만들어줌
    # ( 0은 현재 없는상태, 1는 있는 상태 )
    space = [0] * 13
    diamond = [0] * 13
    heart = [0] * 13
    clover = [0] * 13

    # 중복값 여부 체크
    check = True

    # 카드정보를 모두 꺼냄
    while cards:

        # 카드타입
        card_type = cards.pop(0)

        # Space 경우
        if card_type == 'S':

            # 번호를 끄집어내서 정수형으로 변환
            num = int(''.join([cards.pop(0) for _ in range(2)]))

            # 만약 해당위치가 이미 존재한다면 check -> False 돌리고 멈춤
            if space[num - 1]:
                check = False
                break
            # 존재하지 않는다면 1로 바꿈
            else:
                space[num-1] = 1

        elif card_type == 'D':
            num = int(''.join([cards.pop(0) for _ in range(2)]))

            if diamond[num - 1]:
                check = False
                break
            else:
                diamond[num - 1] = 1

        elif card_type == 'H':
            num = int(''.join([cards.pop(0) for _ in range(2)]))

            if heart[num - 1]:
                check = False
                break
            else:
                heart[num - 1] = 1

        elif card_type == 'C':
            num = int(''.join([cards.pop(0) for _ in range(2)]))

            if clover[num - 1]:
                check = False
                break
            else:
                clover[num - 1] = 1

    # 만약 중복된 값이 있었다면 ERROR 아니면 각 숫자에서 나오지 않은 카드의 개수를 출력
    if check:
        print(f'#{tnt} {space.count(0)} {diamond.count(0)} {heart.count(0)} {clover.count(0)}')
    else:
        print(f'#{tnt} ERROR')
