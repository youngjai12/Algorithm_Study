# https://school.programmers.co.kr/learn/courses/30/lessons/72413
# 어줍잖게 visit 처리를 하고, not visited인 것 중에서 순회하다보니..
# pop()하고 나와서 visit 처리를 해주면, 왔던길을 다시 못돌아보는 상황이 생길수도..!
#   - 만약 동점이고, 더 나은 경로가 이어지는데..

from collections import defaultdict
import heapq

def solution(n, s, a, b, fares):
    graph = defaultdict(list)
    for info in fares:
        graph[info[0]].append((info[1], info[2]))
        graph[info[1]].append((info[0], info[2]))

    def _get_cost_map(ss):
        qq =[]
        cost_map = [100000001] * (n + 1)
        cost_map[ss] = 0

        heapq.heappush(qq, (0, ss))  #(비용, node)
        while qq:
            cur_cost, node_num = heapq.heappop(qq)
            #visited[node_num] = True
            for next, cost in graph[node_num]:
                if cost_map[next] > (cost_map[node_num] + cost):
                    cost_map[next] = cost_map[node_num] + cost
                    heapq.heappush(qq, (cost_map[next] + cost, next))
        return cost_map
    to_a_cost_map = _get_cost_map(a)
    to_b_cost_map = _get_cost_map(b)
    s_cost_map = _get_cost_map(s)

    min_cost = to_a_cost_map[s] + to_b_cost_map[s]
    for idx in range(1, (n+1)):
        if idx == s:
            continue
        tmp_cost = s_cost_map[idx] + to_a_cost_map[idx] + to_b_cost_map[idx]
        min_cost = min(min_cost, tmp_cost)
    return min_cost


if __name__ == '__main__':
    # n = 6 ;s=4; a=6; b=2;
    n=7;s=3;a=4;b=1;
    #fares = [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]]

    fares = [[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]]

    print(solution(n,s,a,b,fares))
