# https://www.acmicpc.net/problem/16118
# 단순히 주어진것에 대해서만 1차적인 비교만 하면 안된다 -> 그 이후에 어떻게 될지도 생각해야한다는 접근은 좋았음.
#   그러나 위 내용을 구현할때, 2차원배열로 생각하면 간단 -> 그럼 모든 경우의수를 tracking할 수 잇음.
#   조건에 따라 지금까지 구해왔던 dist를 둘다 고려해야함.(먼곳으로 돌아왔을때가 최적이 될수도 있으므로...)
#   그러면 여기서 초기에 cost_map설정이 2개니까 잘 고려해줘야함. 모조건 0으로 안하는게 맞는 거임.
# 그리고 다익스트라에서 시간초과 피할때 cost_map < cur_cost 이면 사실 고려할 필요도 없다...(cur_cost에서 next)



import sys
from collections import defaultdict
import heapq

def wolf_travel_tle(graph, points_cnt):
    qq = []
    cost_map = [800000000] * (points_cnt+1)
    cost_map[1]=0

    heapq.heappush(qq, (0, 1, False)) # (cost, node)
    while qq:
        cur_cost, node_num, boosted = heapq.heappop(qq)
        for next_node, cost in graph[node_num]:
            to_next_cost = round(cost/2, 2) if not boosted else 2*cost
            is_possible=False
            is_put=False
            if cost_map[next_node] > to_next_cost+cur_cost:
                cost_map[next_node] = to_next_cost+cur_cost
                is_put=True
                heapq.heappush(qq, ((cur_cost + to_next_cost), next_node, not boosted))
            for nnext, tmp_nnext_cost in graph[next_node]:
                nnext_cost = round(tmp_nnext_cost/2, 2) if boosted else 2*tmp_nnext_cost
                if cost_map[nnext] > cur_cost+nnext_cost + to_next_cost:
                    is_possible=True
            if not is_put and is_possible:
                heapq.heappush(qq, ((cur_cost + to_next_cost), next_node, not boosted))
    return cost_map

def wolf_travel_tle2(graph, points_cnt):
    qq = []
    cost_map = [800000000] * (points_cnt+1)
    cost_map[1]=0

    heapq.heappush(qq, (0, 1, False)) # (cost, node)
    while qq:
        cur_cost, node_num, boosted = heapq.heappop(qq)
        for next_node, cost in graph[node_num]:
            to_next_cost = cost/2 if not boosted else cost*2
            if cost_map[next_node] > to_next_cost+cur_cost:
                cost_map[next_node] = to_next_cost+cur_cost
                heapq.heappush(qq, ((cur_cost + to_next_cost), next_node, not boosted))
            for nnext, tmp_nnext_cost in graph[next_node]:
                nnext_cost = tmp_nnext_cost/2 if boosted else 2*tmp_nnext_cost
                if cost_map[nnext] > cur_cost+nnext_cost + to_next_cost:
                    cost_map[nnext] = cur_cost+nnext_cost + to_next_cost
                    heapq.heappush(qq, ((cur_cost+nnext_cost + to_next_cost), nnext, boosted))
    return cost_map

def wolf_travel(graph, points_cnt):
    cost_map = [[800000000] * (points_cnt+1) for _ in range(2)]
    cost_map[0][1] = 0
    cost_map[1][1] = 0
    qq = []
    heapq.heappush(qq, (0, 1, False))
    while qq:
        cur_cost, node_num, boosted = heapq.heappop(qq)  # boosted=true이면 2배로 현재에 도착했다.
        if boosted and cost_map[1][node_num] < cur_cost:
            continue
        if not boosted and cost_map[0][node_num] < cur_cost:
            continue
        for next_node, cost in graph[node_num]:
            if boosted:
                if cost_map[0][next_node] > cost*2 + cur_cost:
                    cost_map[0][next_node] = cost * 2 + cur_cost
                    heapq.heappush(qq, (cost * 2 + cur_cost, next_node, not boosted))
            else:
                if cost_map[1][next_node] > (cost/2) + cur_cost:
                    cost_map[1][next_node] = (cost/2) + cur_cost
                    heapq.heappush(qq, ( (cost/2) + cur_cost, next_node, not boosted))
    return [min(e,v) for e,v in zip(cost_map[0], cost_map[1])]

def fox_travel(graph, points_cnt):
    qq = []
    cost_map = [999999999] * (points_cnt + 1)
    cost_map[1] = 0

    heapq.heappush(qq, (0, 1))  # (cost, node)
    while qq:
        cur_cost, node_num = heapq.heappop(qq)
        for next_node, cost in graph[node_num]:
            if cost_map[next_node] > cur_cost + cost:
                cost_map[next_node] = cur_cost + cost
                heapq.heappush(qq, ((cur_cost + cost), next_node))
    return cost_map


def solve(graph, points_cnt):
    wolf = wolf_travel(graph, points_cnt)
    fox = fox_travel(graph, points_cnt)
    cnt=0

    for i in range(1, points_cnt+1):
        if wolf[i] > fox[i]:
            cnt+=1
    return cnt




if __name__ == "__main__":
    input = sys.stdin.readline
    N, M = map(int, input().split())
    graph = defaultdict(list)
    for _ in range(M):
        a,b,d = map(int, input().split())
        graph[a].append((b, 2*d))
        graph[b].append((a, 2*d))

    print(solve(graph, N))