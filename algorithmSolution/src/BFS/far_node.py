# https://school.programmers.co.kr/learn/courses/30/lessons/49189
from collections import defaultdict, deque


def make_graph(edge_info):
    graph = defaultdict(list)
    for edge in edge_info:
        graph[edge[0]].append(edge[1])
        graph[edge[1]].append(edge[0])
    return graph

def solution(n, edge):
    graph = make_graph(edge)
    answer = defaultdict(list)
    visited = [False] * (n+1)
    visited[1] = True
    queue = deque()
    queue.append((1,0))
    while queue:
        cur_node, cost = queue.popleft()
        answer[cost].append(cur_node)
        for next_node in graph[cur_node]:
            if not visited[next_node]:
                visited[next_node] = True
                queue.append((next_node, cost + 1))

    max_cost = max(list(answer.keys()))
    return len(answer[max_cost])




if __name__ == "__main__":
    vertex = [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]
    n = 6
    print(solution(n ,vertex))