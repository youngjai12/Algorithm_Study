# https://school.programmers.co.kr/learn/courses/30/lessons/92343
#  트리 형태의 자료구조에서, 순회를 해야해서 트리분류의 문제인듯. 다만, 순회하는 방법은 DFS로..
# 어려웠던 점
#  1) 방문체크하는것 : 왜냐면 왔던 node로 다시 되돌아갈 수 있어야 양있는 node들 쪽으로 넘어갈 수가 있음.
#    그런데 단순히 방문했던 node를 체크하면, 그 node 다른쪽 node로 건너가서 check해볼 수가 없다.
#    => 그래서 여태껏 방문한 노드들을 계속 돌면서 탐색할 수 있도록 함. 
from collections import defaultdict

# edge = [부모, 자식]
def solution(info, edges):
    tree = defaultdict(list)
    for edge in edges:
        tree[edge[0]].append(edge[1])
        tree[edge[1]].append(edge[0])
    # v = [False] * len(info)
    v_nodes = set()

    def _dfs(cur, ship_cnt, wolf_cnt, visited_nodes, mmax):
        mmax = max(mmax, ship_cnt)
        for cur_node in visited_nodes:
            for next_node in tree[cur_node]:
                if next_node not in visited_nodes:
                    if ship_cnt > wolf_cnt or wolf_cnt == 0:
                        visited_nodes.add(next_node)
                        if info[next_node] == 0:
                            mmax = _dfs(next_node, ship_cnt+1, wolf_cnt, visited_nodes, mmax)
                        else:
                            mmax = _dfs(next_node, ship_cnt, wolf_cnt + 1, visited_nodes, mmax)
                        visited_nodes.remove(next_node)
        return mmax


    v_nodes.add(0)
    answer =0
    if info[0] == 0:
        answer = _dfs(0, 1, 0, v_nodes, answer)
    else:
        answer = _dfs(0, 0, 1, v_nodes, answer)
    return answer



max_ship_cnt = 0

def dfs_search(visit, graph, ship, wolf, info, visited_nodes):
    def iss_possible(a_node):
        if info[a_node] == 0:
            return ship + 1 > wolf
        else:
            return ship > wolf + 1
    global max_ship_cnt
    if max_ship_cnt < ship:
        max_ship_cnt = ship
    possible_edges = []
    for a_node in visited_nodes:
        for e in graph[a_node]:
            target_edge = (a_node, e)
            if not visit[target_edge]:
                possible_edges.append(target_edge)
    for edge in possible_edges:
        if iss_possible(edge[1]):
            visit[edge] = True
            if info[edge[1]] == 0:
                dfs_search(visit, graph, ship + 1, wolf, info, [*visited_nodes, edge[1]])
            else:
                dfs_search(visit, graph, ship, wolf + 1, info, [*visited_nodes, edge[1]])
            visit[edge] = False

def solution_old(info, edges):
    graph = defaultdict(list)
    for edge in edges:
        graph[edge[0]].append(edge[1])
    visit = defaultdict(bool)
    for edge in edges:
        visit[tuple(sorted(edge))] = False
    dfs_search(visit, graph, 1,0, info, [0])
    return max_ship_cnt



def fn2(vval):
    vval = vval+2
    return vval

if __name__ == '__main__':
    # info = [0,0,1,1,1,0,1,0,1,0,1,1]
    info = [0,1,0,1,1,0,1,0,0,1,0]
    # edges = [[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]
    edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6],[3,7],[4,8],[6,9],[9,10]]
    print(solution(info, edges))
