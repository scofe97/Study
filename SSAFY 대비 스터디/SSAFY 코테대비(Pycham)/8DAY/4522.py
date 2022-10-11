for tnt in range(1, int(input()) + 1):
    s = list(input())
    rev_s = s[::-1]

    for i in range(len(s)):
        if s[i] == '?':
            s[i] = rev_s[i]
        elif rev_s[i] == '?':
            rev_s[i] = s[i]

    if s == rev_s:
        print(f"#{tnt} Exist")
    else:
        print(f"#{tnt} Not exist")