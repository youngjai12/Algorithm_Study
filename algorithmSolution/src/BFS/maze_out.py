# https://school.programmers.co.kr/learn/courses/30/lessons/159993
# 어려웠던 점
#    1. lever를 안땡긴 상황이었으면 다시 돌아가야한다. 일반 visit 체크로는 안되고, 땡겼을때랑 아닐때 visit
#    2. next가 통로/lever/출구일때는 체크 -> 현시점(pop한후)에서 체크하는 방식
#    3. (이게 핵심) start도 여러번 지나갈 수 있다

from collections import deque

def get_start(maps):
    for x in range(len(maps)):
        for y in range(len(maps[0])):
            if maps[x][y] == "S":
                return (x,y)

def solution(maps):
    queue = deque()
    start_x, start_y = get_start(maps)
    queue.append((start_x, start_y, 0, False))
    dx = [1, 0, -1, 0]
    dy = [0, 1, 0, -1]

    visit_map = {True: [[False] * len(maps[0]) for _ in range(len(maps))],
                 False: [[False] * len(maps[0]) for _ in range(len(maps))]}
    visit_map[False][start_x][start_y] = True

    while queue:
        cur_x, cur_y, nround, lever_met = queue.popleft()
        if maps[cur_x][cur_y] == "L":
            lever_met = True
        elif maps[cur_x][cur_y] == "E":
            if lever_met:
                return nround
        for i in range(4):
            next_x = cur_x + dx[i]
            next_y = cur_y + dy[i]
            if not(0 <= next_x < len(maps) and 0 <= next_y < len(maps[0])):
                continue
            if visit_map[lever_met][next_x][next_y]:
                continue
            if maps[next_x][next_y] in ["O", "L", "E", "S"]:
                visit_map[lever_met][next_x][next_y] = True
                queue.append((next_x, next_y, nround+1, lever_met))
    return -1



if __name__ == '__main__':
    map1 = ["SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"]
    map2 = ["LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"]
    corner_case = ["OOLOOSE"]
    print(solution(corner_case))