for tnt in range(1, int(input()) + 1):
    s1, s2 = map(list, input().split())

    lcs = [[0 for i in range(len(s1) + 1)] for j in range(len(s2) + 1)]

    for i in range(1, len(s1) + 1):
        for j in range(1, len(s2) + 1):

            # 해당방식은 이렇게 비교된다
            # ACAYKP
            # CAPCAK
            # 2개의 단어가 존재함
            # C , A(0)  ->  C, AC(1) -> C, ACA(1) ... -> C, ACAYKP(1)
            # CA, A(1) -> CA, AC(1) -> CA, ACA(2)
            # CAP, A(1) -> CAP, AC(1), -> CAP, ACA(2)

            # 같은 경우
            # C, AC 의 예시가 그렇다
            # 같으면 둘의 문자에서 C가 없는 경우의 최대값에서 +1을 해줌
            # 즉 ''. A 상태이므로 0 에서 + 1 -> 1
            if s1[i - 1] == s2[j - 1]:
                lcs[i][j] = lcs[i - 1][j - 1] + 1


            # 만약 2단어의 알파벳이 다른경우
            # CAP, ACA 의 경우임
            # CAP, AC 와 CA, ACA 의 경우중 최대값을 차용함
            else:
                # 해당위치는,
                lcs[i][j] = max(lcs[i][j - 1], lcs[i - 1][j])

    print(f'#{tnt} {lcs[len(s1)][len(s2)]}')
