# 초기화
def set_start_condition(_center, _board) -> list:
    _board[_center - 1][_center - 1] = 2
    _board[_center - 1][_center] = 1
    _board[_center][_center - 1] = 1
    _board[_center][_center] = 2
    return _board


def count(_board) -> list:
    answer_list = [0, 0]
    for row in _board:
        answer_list[0] = answer_list[0] + row.count(1)
        answer_list[1] = answer_list[1] + row.count(2)
    return list(map(str, answer_list))


def take_action(action, _board) -> list:
    # 8방향 탐색 : 위, 위오, 오, 아오, 아래, 아왼, 왼, 위왼
    dx = [0, 1, 1, 1, 0, -1, -1, -1]
    dy = [-1, -1, 0, 1, 1, 1, 0, -1]
    x, y, value = action[0]-1, action[1]-1, action[2]
    _board[y][x] = value  # 초기 명령 값, 보드에 반영
    
    
    for i in range(len(dx)):
        
        current_x, current_y = x + dx[i], y + dy[i]
        
        while True:
            if not (0 <= current_x < n and 0 <= current_y < n and _board[current_y][current_x] != 0 and _board[current_y][current_x] != value ): break
            else:
                _board[current_y][current_x] = value
                current_x, current_y = current_x + dx[i], current_y + dy[i]
    return _board


def solution(_n, _mlist) -> list:
    board = [[0] * _n for row in range(_n)]  # 빈 보드 생성 (주의 : [[0]*_n]*_n)
    board = set_start_condition(int(_n / 2), board)  # 초기 보드 상태 설정
    for _l in _mlist:
        board = take_action(_l, board)
    return count(board)


for t in range(int(input())):
    n, m = map(int, input().split())
    m_list = [list(map(int, input().split())) for i in range(m)]
    answer = solution(n, m_list)
    print("#" + str(t + 1), " ".join(answer))