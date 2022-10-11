for tnt in range(1, int(input())+1):
    
    # * 버스가 움직이는 경로 수
    bus_move_cnt = int(input())
    # * 입력받음
    bus_move = []
    for _ in range(bus_move_cnt):
        bus_move.append(list(map(int,input().split())))
        
    # * 버스 정류장 개수
    bus_stop_cnt = int(input())
    # * 각 버스정류장의 번호받음
    bus_stop = []
    for _ in range(bus_stop_cnt):
        bus_stop.append(int(input()))
    
    
    result = [0] * bus_stop_cnt
    
    # * 버스 정류장이, 경로에 몇번 포함되는지 체크해서 더함
    for move in bus_move:
        for stop in range(bus_stop_cnt):
            if move[0] <= bus_stop[stop] and move[1] >= bus_stop[stop]:
                result[stop] += 1
                
    result = ' '.join(list(map(str, result)))
    print(f'#{tnt} {result}')