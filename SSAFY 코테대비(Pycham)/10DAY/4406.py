for tnt in range(1, int(input())+1):

    s = list(input())

    for i in range(len(s)):
        if s[i] in ['a','e','i','o','u']:
            # 만약 해당 idx 글자가 모음이면 -1로 변환
            s[i] = '-1'

    # 리스트를 문자열로 바꾸고, -1을 지워줌
    result = ''.join(s).replace('-1', '')

    print(f'#{tnt} {result}')
