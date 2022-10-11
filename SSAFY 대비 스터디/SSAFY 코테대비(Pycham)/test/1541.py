s = input()

number = list(map(int, list(s.replace('+', ' ').replace('-', ' ').split())))
op = []

for i in list(s):
    if i == '+' or i  == '-':
        op.append(i)

result = number[0]
step = 0
check = False

for i in range(len(number) - 1):
    if op[i] == '-' and not check:
        check = True
        step += number[i+1]

    elif op[i] == '+' and not check:
        result += number[i + 1]

    elif op[i] == '+' and check:
        step += number[i + 1]

    elif op[i] == '-' and check:
        result -= step
        step = number[i + 1]


print(result - step)

