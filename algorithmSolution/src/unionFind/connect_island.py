# https://school.programmers.co.kr/learn/courses/30/lessons/42861

# 전형적인 MST 구현 문제
#   - 가중치가 그냥 최소가 되는걸 계속 선택하면 됨 -> 그래서 greedy
# 첫 트 -> 명시된 TC만 맞고 다 틀림...왜..?
#   :


def find_root(x, graph):
    if x == graph[x]:
        return x
    else:
        graph[x] = find_root(graph[x], graph)
        return graph[x]

def union(x, y, graph):
    root_x = find_root(x, graph)
    root_y = find_root(y, graph)
    if root_x > root_y:
        graph[root_y] = root_x
    elif root_x < root_y:
        graph[root_x] = root_y

def solution_wrong(n, costs):

    # 초기에는 당연히 자기자신 -> 각자가 각자의 집합
    root_info = [i for i in range(n)]
    sorted_cost = sorted(costs, key=lambda x: x[2], reverse=False)
    ccost = 0
    for info in sorted_cost:
        if root_info[info[0]] != root_info[info[1]]:
            union(info[0], info[1], root_info)
            ccost += info[2]
        if len(set(root_info)) == 1:
            return ccost

def solution(n, costs):
    root_info = [i for i in range(n)]
    sorted_cost = sorted(costs, key=lambda x: x[2], reverse=False)
    ccost = 0
    for info in sorted_cost:
        if find_root(info[0], root_info) != find_root(info[1], root_info):
            union(info[0], info[1], root_info)
            ccost += info[2]

    # 어차피 부모노드가 다를때만 union해주고 그때서야 ccost에 더해주니까.. 굳이 모두가 같은 root-node인지 볼필욘 없을듯
    # 그렇게 했더니, 부모노드가 듬성듬성하면..문제가..
    return ccost


if __name__ == '__main__':
    costs = [[0,1,1],[1,2,1],[1,3,1],[3,4,1],[3,15,1],[0,4,1]]
    print(solution(4, costs))