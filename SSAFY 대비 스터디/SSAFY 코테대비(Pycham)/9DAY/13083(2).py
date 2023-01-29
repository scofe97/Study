def solution(_n, _arr) -> int:

    open_days = []

    for i in range(len(_arr)):
        if _arr[i] == 1:
            open_days.append(i)

    result = 100000*7+1
    for _index in open_days:
        days = count = 0

        while count != _n:
            for i in range(7):
                index = (_index + i) % 7

                if _arr[index]:
                    count += 1
                days += 1

                if count == _n:
                    result = min(days, result)
                    break

    return result



for t in range(int(input())):
    cnt = int(input())
    class_arr = list(map(int, input().split()))
    answer = solution(cnt, class_arr)
    print(f"#{t + 1} {answer}")
