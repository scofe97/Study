# 진법 변환
n = 'FF'
print(int(n, 16)) # 255

print(bin(10)) # 0b1010
print(oct(10)) # 0o12
print(hex(10)) # 0xa


import string
tmp = string.digits + string.ascii_lowercase

def convert(num, base):
    q, r = divmod(num, base) # divmod 몫과 나머지 계산

    if q == 0:
        return tmp[r]
    else:
        return convert(q, base) + tmp[r]



# 리스트 관련 함수
arr = [i for i in range (1, 1000)]


print(arr[1::2])
print(arr[::2])


arr2 = [[1,2,3], [4,5,6], [7,8,9]]
arr2_reverse = list(map(list,zip(*arr2)))


print(arr2) # [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
print(arr2_reverse) # [[1, 4, 7], [2, 5, 8], [3, 6, 9]]


a = [i for i in range(1,6)]
b = [i for i in range(1,6,2)]


a_sub_b = [x for x in a if x not in b]
print(a_sub_b) # [2, 4]


students = [ ('홍길동', 3.9, 2016303),('김철수', 3.0, 2016302),('최자영', 4.3, 2016301) ]
print(sorted(students, key = lambda student:student[2]))
# [('최자영', 4.3, 2016301), ('김철수', 3.0, 2016302), ('홍길동', 3.9, 2016303)]

students.sort(key=lambda student: student[0])
print(students)
# [('김철수', 3.0, 2016302), ('최자영', 4.3, 2016301), ('홍길동', 3.9, 2016303)]


test_list = [i for i in range(1,11)]

test_list.append(11)
print(test_list) # [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

test_list.insert(0, 0)
print(test_list) # [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

test_list.remove(0)
print(test_list) # [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

test_list.clear()
print(test_list) # []


## 90도 뒤집기
matrix = [[1,2,3],[4,5,6],[7,8,9]]

matrix_90 = list(map(list, zip(*matrix[::-1])))
matrix_180 = list(map(list, zip(*matrix_90[::-1])))
matrix_270 = list(map(list, zip(*matrix_180[::-1])))




# 문자열 관련
a = 'How are you? Python3!'
print(a.upper()) # HOW ARE YOU? PYTHON3! (모두 대문자)
print(a.lower()) # how are you? python3! (모두 소문자)
print(a.title()) # How Are You? Python3! (각 단어 첫알파벳 대문자)
print(a.capitalize()) # How are you? python3! (첫 시작 알파벳 대문자)


a = '  abc  '
## strip 로 공백이나 줄바꿈, tab 문자도 제거 가능
print(a.lstrip()) # 'abc  '
print(a.rstrip()) # '  abc'
print(a.strip()) # 'abc'

a = '00110100000001'
b = a.replace('0', ' ').split()
print(b) # ['11', '1', '1']



# 기타 공식
def gcd(a, b):
    if b == 0:
        return a
    else:
        return gcd(b, a%b)

def lcd(a, b):
    return a * b // gcd(a, b)

print(gcd(100,80))
print(lcd(100,80))


