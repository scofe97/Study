
decode = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
          'W', 'X', 'Y', 'Z',
          'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
          'w', 'x', 'y', 'z',
          '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
          ]

for tnt in range(1, int(input())+1):

    word = list(input())
    length = len(word)
    res = ''

    for c in word:

        num = decode.index(c)
        num2 = bin(num)[2:]

        while len(num2) < 6:
            num2 = '0'+num2

        res += num2

    r = ''

    for i in range(length*6//8):
        num = int(res[i*8 : i*8+8],2)
        r += chr(num)

    print(f'#{tnt} {r}')

#문제 요약
# 1. 표1을 보고 입력받은 문자들을 각각 대응하는 숫자로 변경한다.
# 2. 각 숫자들을 6자리 이진수로 표현하고, 이 이진수들을 한 줄로 쭉 이어 붙인다.
# 3. 한 줄로 쭉 이어붙인 이진수들을 8자리씩 끊어서 십진수로 바꾼다.
# 4. 각각의 십진수를 아스키 코드로 변환한다.
