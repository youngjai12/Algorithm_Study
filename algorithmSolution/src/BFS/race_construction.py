# https://school.programmers.co.kr/learn/courses/30/lessons/67259
# 반례 생각못할뻔 -> 도착은 빠른데, 경로비용은 많이걸릴수도 있음.
# 반례 2: 같은 비용으로 도착햇는데, 오기까지 그 꺾어지는 경로가 달랏을 수 잇다.
#       - 그러다보면 그 다음으로 경로를 나아갈떄, 꺾어지느냐, 직진이야 그 진로가 다를 수가 있어서..
# 반례3 : 비용이 작은상태로 방문했으면 무조건 고려할 필요가 없을줄알았는데..그게아니었음. 그 다음 경로가 꺾냐, 직진이냐에 따라 또 다름.

from collections import deque


def solution(board):
    visit = [[False] * len(board[0]) for _ in range(len(board))]
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]
    min_cost_list = []

    def _dfs(x, y, cost, prev_direction):
        if x == len(board[0])-1 and y == len(board)-1:
            min_cost_list.append(cost)
        else:
            for i in range(4):
                next_x = x + dx[i]
                next_y = y + dy[i]
                if not (0 <= next_x < len(board[0]) and 0 <= next_y < len(board)):
                    continue
                if visit[next_y][next_x] or board[next_y][next_x] == 1:
                    continue

                visit[next_y][next_x]=True
                if prev_direction and prev_direction != i:
                    _dfs(next_x, next_y, cost+600, i)
                else:
                    _dfs(next_x, next_y, cost+100, i)
                visit[next_y][next_x] = False
    _dfs(0, 0, 0, None)
    return min_cost_list

def solution_bfs(board):
    queue = deque()
    visit = [[99999999999] * len(board[0]) for _ in range(len(board))]
    visit[0][0]=0
    # (x, y, straight, corner, dir)
    queue.appendleft((0, 0, 0, -1))

    # 0:서, 1:남, 2:동, 3:북
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    global_min_cost = 9999999999
    while queue:
        x, y, cost, direction = queue.popleft()
        if x == len(board[0])-1 and y == len(board)-1:
            global_min_cost = min(global_min_cost, cost)
        for i in range(4):
            next_x = x + dx[i]
            next_y = y + dy[i]
            if not (0 <= next_x < len(board[0]) and 0 <= next_y < len(board)):
                continue
            if board[next_y][next_x] == 1:
                continue

            if direction != -1 and direction != i:
                if cost + 600 <= visit[next_y][next_x]:
                    visit[next_y][next_x] = min(visit[next_y][next_x], cost + 600)
                    queue.append((next_x, next_y, cost+600, i))
            else:
                if cost + 100 <= visit[next_y][next_x]:
                    visit[next_y][next_x] = min(visit[next_y][next_x], cost + 100)
                    queue.append((next_x, next_y, cost+100, i))

    print_map(visit)
    return global_min_cost

def print_map(b):
    for i in range(len(b)):
        print(b[i])

if __name__ == '__main__':
    # target_board = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
    # target_board =[[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0],[0,0,0,0,0,1,0,0],[0,0,0,0,1,0,0,0],[0,0,0,1,0,0,0,1],[0,0,1,0,0,0,1,0],[0,1,0,0,0,1,0,0],[1,0,0,0,0,0,0,0]]
    target_board = [[0, 0, 0, 0, 0],[0, 1, 1, 1, 0],[0, 0, 1, 0, 0],[1, 0, 0, 0, 1],[1, 1, 1, 0, 0]]
    print_map(target_board)
    #target_board = [[0,0,1,0],[0,0,0,0],[0,1,0,1],[1,0,0,0]]
    #target_board = [[0,0,0,0,0,0],[0,1,1,1,1,0],[0,0,1,0,0,0],[1,0,0,1,0,1],[0,1,0,0,0,1],[0,0,0,0,0,0]]
    print(solution_bfs(target_board))