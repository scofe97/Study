import time


start = time.time()
# * 모든결과를 문자열로 바꿔줌
test = {str(i * j)  for i in range(1000) for j in range(1000)}
for i in test:
    i = i + '변환'

print(f'{time.time() - start}')
# 0.2830634117126465 (시간)



start = time.time()
# * set으로 중복제거되고 문자열로 바꿈
test = {(i * j)  for i in range(1000) for j in range(1000)}
for i in test:
    i = str(i) + '변환'
    
print(f'{time.time() - start}')
# 0.16303706169128418 (시간)
