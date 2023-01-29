for tnt in range(1, int(input()) + 1):
    arr = list(input().split())

    time = 0

    blue_point = 1
    blue_time = 0

    orange_point = 1
    orange_time = 0

    op_count = int(arr.pop(0))

    for i in range(op_count):
        color = arr.pop(0)
        point = int(arr.pop(0))

        if color == 'B':
            if time == blue_time:
                blue_time += abs(blue_point - point) + 1
                blue_point = point
                time = blue_time
            else:
                if time - blue_time > abs(point - blue_point):
                    time += 1
                    blue_point = point
                    blue_time = time

                else:
                    time += abs(blue_point - point) - (time - blue_time) + 1
                    blue_point = point
                    blue_time = time


        elif color == 'O':
            if time == orange_time:
                orange_time += abs(orange_point - point) + 1
                orange_point = point
                time = orange_time
            else:
                if time - orange_time > abs(point - orange_point):
                    time += 1
                    orange_point = point
                    orange_time = time

                else:
                    time += abs(orange_point - point) - (time - orange_time) + 1
                    orange_point = point
                    orange_time = time



    print(f'#{tnt} {time}')
