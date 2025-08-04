# https://school.programmers.co.kr/learn/courses/30/lessons/12978
from collections import defaultdict
import heapq


class PriorityQueue:
    def __init__(self):
        self.queue = list()
    def add(self, e):
        self.queue.append(e)
    def pop(self):
        min_val = 999999999999
        idx = None
        for i, ee in enumerate(self.queue):
            if ee[0] < min_val:
                min_val = ee[0]
                idx = i

        return self.queue.pop(idx)
    def __len__(self):
        return len(self.queue)

def make_graph(roads):
    graph = defaultdict(list)
    for road in roads:
        graph[road[0]].append((road[1], road[2]))
        graph[road[1]].append((road[0], road[2]))
    return graph

def solution(N, road, K):
    cost = [999999999] * (N+1)
    cost[1] = 0
    graph = make_graph(road)
    queue = []

    heapq.heappush(queue, (0, 1))
    while queue:
        cur_cost, node_num = heapq.heappop(queue)
        for next_node, next_cost in graph[node_num]:
            if cost[next_node] > (cur_cost + next_cost):
                cost[next_node] = cur_cost + next_cost
                heapq.heappush(queue, (cur_cost + next_cost, next_node))


    return len([e for e in cost[1:] if e <= K])



if __name__ == "__main__":
    #road = [[1,2,1],[2,3,3],[5,2,2],[1,4,2],[5,3,1],[5,4,2]]
    road = [[1,2,1],[1,3,2],[2,3,2],[3,4,3],[3,5,2],[3,5,3],[5,6,1]]
    N = 6; K = 4
    print(solution(N, road, K))