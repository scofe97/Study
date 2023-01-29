for tnt in range(1, int(input())+1):

    s = list(input())

    year = ''.join(s[:4])
    month = ''.join(s[4:6])
    day = ''.join(s[6:8])

    day_num = int(day)
    month_num = int(month)

    if month_num in [1,3,5,7,8,10,12] and 1<= day_num <= 31:
        print(f'#{tnt} {year}/{month}/{day}')

    elif month_num in [4,6,9,11] and 1<= day_num <= 30:
        print(f'#{tnt} {year}/{month}/{day}')

    elif month_num == 2 and 1<= day_num <= 28:
        print(f'#{tnt} {year}/{month}/{day}')

    else:
        print(f'#{tnt} -1')


