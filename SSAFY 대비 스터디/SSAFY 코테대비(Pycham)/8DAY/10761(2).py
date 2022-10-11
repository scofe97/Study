class Robot:
    def __init__(self):
        # 처음위치는 1이고, 시간은 1이다.
        self.location = 1
        self.time = 0

    def status(self):
        print(f'위치:{self.location}, 시간:{self.time}')


def operate(robot, button):  # 현재위치와 눌러야 되는 버튼
    global time

    # 시간이 같으면 로봇이동
    if time == robot.time:
        # 해당색상의 로봇은 button 까지 움직이고 눌려야함
        robot.time += abs(robot.location - button) + 1
        # 위치는 button 위치
        robot.location = button
        # 걸린시간
        time = robot.time
    else:
        # 시간이 충분하다면 로봇은 이미 이동했다
        if time - robot.time > abs(robot.location - button):
            time += 1  # 버튼 누르는 시간
            robot.time = time
            robot.location = button
        else:  # 시간이 충분하지 않았다면 로봇은 시간차이만큼만 이동 후 나머지 이동
            time += abs(robot.location - button) - (time - robot.time) + 1
            robot.location = button
            robot.time = time
    return


for tc in range(int(input())):

    # 변수받음
    tmp = input().split()
    N = int(tmp[0])
    orders = tmp[1:]

    # 로봇생성
    orange = Robot()
    blue = Robot()
    time = 0

    # N 번만큼 명령수행
    for i in range(N):

        # 로봇타입과, 번호
        r, o = orders[i * 2], orders[i * 2 + 1]
        if r == 'O':
            operate(orange, int(o))
        else:
            operate(blue, int(o))

    print(f'#{tc + 1} {time}')