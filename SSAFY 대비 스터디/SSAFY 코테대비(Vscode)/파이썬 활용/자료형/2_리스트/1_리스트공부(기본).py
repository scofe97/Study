# https://blockdmask.tistory.com/425

# ? 리스트란?
# n 점검하기나 기억하기 쉽도록 특별한 순서로 적는 것


# n 1. 리스트 생성방법
# * 리스트이름 = [요소1, 요소2]
# * 변수 = list()



# n 2. 리스트의 덧셈, 곱셈, 변경
a = [1,2,3]
b = [4,5,6]

print(a+b) # [1,2,3,4,5,6]
c = a*3
print(c)
c[0] = 5
print(a*3) # [1,2,3,1,2,3,1,2,3]
print(c)
print(a*0) # []



# n  3. 리스트 검색
# * 양수 0 ~ n-1
# * 음수 -n ~ -1
a = [10, 20, 30, 40, 50, 60]
print(a[1]) # 20
#print(a[6]) # error
print(a[-1]) # 60
print(a[-6]) # 10
#print(a[-7]) # error
print(a[1:3]) # [20, 30]
print(a[3:1]) # []
print(a[-2: -1]) # [50]
print(a[-2:]) # [50, 60]

print(a[:]) # [10, 20, 30, 40, 50, 60]
print(a[2:]) # [30, 40, 50, 60]
print(a[:-2]) # [10, 20, 30, 40]

print(a[:: 2]) # [10, 30, 50]
print(a[:: -1]) # [60, 50, 40, 30, 20, 10]
print(a[:: 3]) # [10, 40]

print(a[:][:1:3]) # [10]
print(a[1:6][2:5]) # [40, 50, 60]



# n 4. 리스트의 길이, 삭제
a = [1,2,3,4,5,6,7,8,9,10]

print(len(a))
del(a[1:2])
print("삭제 [1:2]",a) # [1, 3, 4, 5, 6, 7, 8, 9, 10]
del(a[-2:])
print(a) # [1, 3, 4, 5, 6, 7, 8]



# n 5. 리스트 메서드
a = [1,2,3]

# * append - 리스트에 값 추가
a.append(4)
a.append(1)
print(a) # [1, 2, 3, 4, 1]

# * insert(a,b) - a위치에 b값을 추가
a.insert(0,-2)
a.insert(1,-1)
print(a) # [-2, -1, 1, 2, 3, 4, 1]

# * remove(x) - 특정값 제거
a.remove(-1)
print(a) # [-2, 1, 2, 3, 4, 1]
a.remove(1) # 1이 2개이상 있으면 앞에꺼부터 지움
print(a) # [-2, 2, 3, 4, 1]

# * pop() - 마지막 값 반환후 삭제
print(a.pop())

# * list.extend(list2) - list1 뒤에 list2 를 연결
# ? +와 다른점은 +는 list3 = list1 + list2 처럼 새로운 리스트 반환
# ? extend는 list1 자체에 붙이는 것

list1 = [1,2]
list2 = [3,4]
list1.extend(list2)
print(list1) # [1, 2, 3, 4]



# n 6. 파이썬 메서드
a = [1,2,3]

# * copy - 복사
b = a.copy()
print(b) # [1, 2, 3]

# ? 주소가 다르게 생기므로 새로운 리스트가 생긴걸로 볼수있다
print(id(a)) # 1984241106432
print(id(b)) # 1984240842752

# * reverse - 리스트 뒤집기
a.reverse()
print(a)

# * sort - 정렬
# ? 리스트 요소들이 비교가 가능해야 에러가 안나옴
# ? a.sort() 자체는 출력해도 아무런값도 안나옴 정렬하고 출력해야함
a.sort()
print(a)

# * count(x) - 리스트의 x값 세기
a.append(1)
a.append(1)
a.append(2)
print(a)
print(a.count(1)) # 3
print(a.count(2)) # 2

# * index(x) - x의 위치찾기
a = [1,2,3]
print(a.index(2)) # 1

# * clear() - 초기화
a.clear()
print(a)



# n 7. 얕은복사, 깊은복사
# ? 얕은복사 -> 복사는 했지만 동일 리스트를 가리킴
# ? 깊은복사 -> 서로 독립된 리스트임

a = [1,2,3,4,5]

# * 얕은복사
b = a
print(id(a)) # 2485000371840
print(id(b)) # 2485000371840

# * 깊은복사
b = a.copy()
c = a[:]
print(id(a)) # 2485000371840
print(id(b)) # 2669539982976
print(id(c)) # 2669539715968