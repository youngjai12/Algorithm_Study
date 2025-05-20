# https://school.programmers.co.kr/learn/courses/30/lessons/49994
# 어려웠던 점은
#    1. 경계선에서 더 가라는 조건이 주어질때 그것들 처리하는것
#    2. 점을 기준으로만 visit 처리하면, 이미 왔다고 처리되는것


from collections import defaultdict


def move(cur_loc, dir):
    dir_mapper = {"U": 1, "D": 3, "L": 0, "R": 2}
    dx = [-1, 0, 1, 0]
    dy = [0, 1, 0, -1]

    next_x = cur_loc[0] + dx[dir_mapper[dir]]
    next_y = cur_loc[1] + dy[dir_mapper[dir]]
    final_x = cur_loc[0]
    final_y = cur_loc[1]

    if -5 <= next_x <= 5:
        final_x = next_x
    if -5 <= next_y <= 5:
        final_y = next_y
    return (final_x, final_y)


def bf_sol(dirs):
    def is_visited(cur_loc, next_loc, visit_arr):
        if visit_arr[(cur_loc[0], cur_loc[1], next_loc[0], next_loc[1])]:
            return True
        if visit_arr[(next_loc[0], next_loc[1], cur_loc[0], cur_loc[1])]:
            return True
        return False


    visited = defaultdict(bool)
    cur_loc = (0,0)
    prev_loc = (-100, -100)
    path=0
    for dir in dirs:
        nextx, nexty = move(cur_loc, dir)
        if not (nextx==cur_loc[0] and nexty==cur_loc[1]) and not is_visited((nextx, nexty), cur_loc, visited):
            visited[(nextx, nexty, cur_loc[0], cur_loc[1])] = True
            path += 1
        prev_loc = (cur_loc[0], cur_loc[1])
        cur_loc = (nextx, nexty)

    return path

if __name__ == '__main__':
    print(bf_sol("ULURRDLLU"))
