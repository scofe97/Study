import math


def problem_3809(cnt):
    global result_arr, result

    # cnt -> 0~배열의크기 (0이 길이 1을 뜻함)
    # 이 경우는 배열의 모든값이 연속적이므로, 최대값에서 +1한 값을 준다
    if cnt == size:
        result = max(result_arr) + 1
        return

    # 배열의 cnt+1 길이만큼의 수열을 모두 뽑아냄
    for i in range(size - cnt):
        # 문자열을 정수화로 바꿔주는 것
        result_arr.append(int(''.join(a[i:i + cnt + 1])))

    # 중복값을 없애기위해 잠시 set -> list 과정을 거침
    result_arr = list(set(result_arr))
    # 값을 오름차순으로 정렬
    result_arr.sort()

    # 배열의 길이만큼 반복한다
    # 0 -> 배열의 길이만큼 연속된 숫자중 만약 동일하지 않으면 해당 숫자를 리턴한다.
    for n in range(len(result_arr)):
        if result_arr[n] != n:
            result = n
            return

    # cnt+1 길이가 모두 만족하면 cnt+2로 다시 반복한다.
    problem_3809(cnt + 1)


for tnt in range(1, int(input()) + 1):

    # 변수입력
    size = int(input())
    a = ''

    # 문제의 핵심
    # 문자를 길이만큼을 받는대신 줄바꿈이 있을 수 있으며 글자 수 또한 무작위다
    # 고로 글자수가 맞을때까지 계속해서 입력받아야함
    while True:
        a += ''.join(map(str, input().split()))
        if len(a) == size:
            break

    result_arr = []
    result = 0
    problem_3809(0)

    print(f'#{tnt} {result}')
