# 암호코드 규칙
# 총 8개의 숫자 , 앞 7자리는 고유의 번호 , 마지막 자리는 검증 코드
# ex) 8801234
# (홀수 자리의 합 * 3) + 짝수 자리의 합 + 검증코드

for tnt in range(1, int(input())+1):

    n, m = map(int, input().split())
