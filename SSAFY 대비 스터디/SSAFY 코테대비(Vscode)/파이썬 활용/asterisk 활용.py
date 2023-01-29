# https://hwiyong.tistory.com/193
# * Asterisk(*) 을 활용하는 방법



# n 1. 곱셈 및 거듭제곱 연산
print(2*3) # 6
print(2**3) # 8



# n 2. 리스트형 컨테이너 타입의 데이터 반복확장
zeros_list = [0] * 5
zeros_tuple = (0,) * 5

print(zeros_list) # [0, 0, 0, 0, 0]
print(zeros_tuple) # (0, 0, 0, 0, 0)



# n 3. 가변인자를 사용하고자 할 때
# * 가변인자란? -> 길이가 변할 수 있는 argument를 말함
# * 함수에 *args, **kwargs 라고 되있는 표현들 (가변인자를 사용하겠다) 
# * 둘의 차이는 positional 과 keyward 인자의 차이입니다.
# * 밑의 a가 positional arguments, b는 keyword argument 이다.
def function(a, b = None):
 print(a,b)
 
def args_function(*args):
    print(args)

def kwargs_function(**kwargs):
    print(kwargs)
    
args_function('a', 'b') # ('a', 'b')
kwargs_function(a = 100, b = 200) # {'a':100, 'b':200}



# n 4. Unpacking 
# * 알고리즘을 짜다보면 결과부분을 출력할 때 for-loop 을 이용하여 list를 순회하며 출력하는 경우가 있다

test = [1,2,3,4]
print(*test) # 1,2,3,4

test = (5,6,7,8)
print(*test) # 5,6,7,8


test = [1, 2, 3, 4, 5]

*a, b = test
print(a, b) # [1, 2, 3, 4], 5

a, *b, c = test
print(a, b, c) # 1, [2, 3, 4], 5