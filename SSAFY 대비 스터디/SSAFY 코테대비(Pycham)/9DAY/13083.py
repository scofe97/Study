def problem_13083(idx, c) -> int:

    # 계산방법
    # 처음 일주일을 제외하면, 일주일동안 듣는 방식은 모두 같다
    # idx -> 처음 일주일에서 어느 날짜부터 머무르는지 의미
    day = 0
    class_open = False


    for j in range(idx, 7):

        # 만약 수업이 있다면 그때부터 날짜를 계산함 ( 아래에서 설명한 1번요소 )
        if class_arr[j] == 1:
            class_open = True
            c -= 1

        if class_open:
            day += 1

        if c == 0:
            return day

    # 1주일 이후부터는 수업을 모두 들을때 까지 반복함
    while True:
        for j in range(7):
            if class_arr[j] == 1:
                class_open = True
                c -= 1

            if class_open:
                day += 1

            if c == 0:

                return day


for tnt in range(1, int(input())+1):
    # 변수입력받음
    cnt = int(input())
    class_arr = list(map(int, input().split()))
    # 최대 입력값이 10^5이고, 1주일에 수업이 하나인경우, *7이기에 이렇게 설정함
    result = 100000*7+1

    # 해당 문제에서 가장 중요한부분
    # 1. 단순 월요일부터 체류하는게 아닌 , 수업이 시작하는 날짜부터 계산해야 최소값이 나온다.
    # 2. 또한 0 1 0 0 0 1 1 의 경우 월요일이 아닌 금요일 부터 체류해야함

    # 그렇기에 각 요일별로 계산해서 최소날짜를 구함
    for i in range(7):
        result = min(result, problem_13083(i, cnt))


    print(f'#{tnt} {result}')