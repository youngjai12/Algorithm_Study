# https://school.programmers.co.kr/learn/courses/30/lessons/42892
# 전위순회(RootLR), 후위순회(LRRoot)
#   - 굳이 tree로 build하지 않더라도 comparable 구현해서 sorting해도 되지않을까..?
#   - nested한 node 형태로 데이터를 담아야 순회가 쉬울것 같기도 한데...
# 시도1
#   - height을 key로 하는 dictionary를 만들었음. 그리고 각 value는 Node들임.
#   - 이렇게 하니까 층별로의 접근은 쉽지만, 층내에서 3개이상의 node가 있으면, 어느 parent의 child인지 구분하는게 까다로움
#     (왜냐면 왼쪽 parent보단 크고, 오른쪽 parent보단 작은데, 이게 왼쪽 child인지 오른쪽 child인지가 아려움
# 시도 2
#   - 시도 1에서 보면 결국 적절한 child-node의 위치 찾는건 위에서부터 타고 내려와야하는듯해보임. 따라서.. 재귀로 위치찾아가도록..

from sys import setrecursionlimit
setrecursionlimit(10000)


def solution(nodeinfo):
    tree = build_tree(nodeinfo)
    preorder_list = preorder(tree)
    postorder_list = post_order(tree)
    return [preorder_list, postorder_list]

def preorder(a_tree):
    answer = []
    def _print(cur_node):
        answer.append(cur_node.cur)
        if cur_node.left is not None:
            answer.append(_print(cur_node.left))
        if cur_node.right is not None:
            answer.append(_print(cur_node.right))
    _print(a_tree.root)
    return [e for e in answer if e]

def post_order(a_tree):
    answer = []
    def _print(cur_node):
        if cur_node.left is not None:
            answer.append(_print(cur_node.left))
        if cur_node.right is not None:
            answer.append(_print(cur_node.right))
        answer.append(cur_node.cur)
    _print(a_tree.root)
    return [e for e in answer if e]


class Node:
    def __init__(self, cur, x, l, r):
        self.cur = cur
        self.x = x
        self.left = l
        self.right = r

class Tree:
    def __init__(self, root_node):
        self.root = root_node
    def add(self, target_node):
        def _find(cur_node: Node):
            if cur_node.x < target_node.x:
                if cur_node.right is not None:
                    _find(cur_node.right)
                else:
                    cur_node.right = target_node
            if cur_node.x > target_node.x:
                if cur_node.left is not None:
                    _find(cur_node.left)
                else:
                    cur_node.left = target_node
        _find(self.root)

def build_tree(nodeinfo):
    sorted_arr = sorted([(idx+1,x, y) for idx, (x,y) in enumerate(nodeinfo)], key=lambda e: (e[2], -1*e[1]), reverse=True)
    root_node = Node(cur=sorted_arr[0][0], x=sorted_arr[0][1], l=None, r=None)
    tree = Tree(root_node)
    for idx in range(1, len(sorted_arr)):
        target_node = Node(cur=sorted_arr[idx][0], x=sorted_arr[idx][1], l=None, r=None)
        tree.add(target_node)

    return tree






if __name__ == "__main__":
    nodeinfo = [[5,3]]
    print(solution(nodeinfo))