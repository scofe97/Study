result = []
for tnt in range(1, int(input())+1):
    num = input()

    # 길이가 1을 넘어서면 계속 변환해준다.
    while len(num) > 1:
        # 슷지 믄지열을 정수형 리스트로 변환한 합을 다시 문자열로 바꿈
        num = str(sum(list(map(int, list(num)))))

    result.append(f"#{tnt} {num}")

for i in result:
    print(i)


