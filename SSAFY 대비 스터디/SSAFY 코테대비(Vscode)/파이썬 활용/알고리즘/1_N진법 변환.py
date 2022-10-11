
# * n진수 -> 10진수
# n 파이썬에서는 기본적으로 int() 라는 함수를 지원해준다
# n int( string, base(진법) )

print(int('101', 2))
print(int('202', 3))
print(int('303', 4))
print(int('404', 5))
print(int('505', 6))
print(int('ACF', 16))



# * 10진수 -> 2,8,16 진수
# n bin(),  oct(),  hex() 함수를 지원한다

print(bin(11)) # 0b1011
print(oct(11)) # 0o13
print(hex(11)) # 0xb



# * 10진수 -> n진수
# n int같은 함수가 없기 때문에 코드작성이 필요함
import string
tmp = string.digits+string.ascii_lowercase # 0123456789abcdefghijklmnopqrstuvwxyz

# * divmod -> 몫과 나머지가 나옴 a//b, a%b -> divmod(a, b)
def convert(num, base) :
    q, r = divmod(num, base) 
    if q == 0 :
        return tmp[r] 
    else :
        return convert(q, base) + tmp[r]

print(convert(31,16)) 



# * n진수 -> n진수
# n 위의 solution 함수를 활용하면 됨
print(convert(int('c', 16), 4)) # 30(12)
