num = int(input())
arr = []
for i in range(1, num+1):

    s = str(i)

    cnt3 = s.count('3')
    cnt6 = s.count('6')
    cnt9 = s.count('9')
    cnt_sum = cnt3 + cnt6 +cnt9

    if cnt_sum:
        arr.append('-'*cnt_sum)
    else:
        arr.append(i)

print(*arr)

# 05:02