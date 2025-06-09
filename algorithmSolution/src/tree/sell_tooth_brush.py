# https://school.programmers.co.kr/learn/courses/30/lessons/77486
#  dfs로 뭔가를 탐색할때, 바로 return하면 안된다.
#  바로 Return 안하려고, for문으로 next후보들 돌면서 할당하는 방식으로하면, 마지막에 None이게 되면 잘 찾았더라도, None이된다..
#  earn 해주는건 재귀로 해준다.
#  첫번째 방법이 시간초과가 났다... -> root부터 쭉 타고들어가야해서.. tree순회를 해서 그런것 아닐까... linkedlist 형태의 tree의 한계인듯함.

import math


class NodeOld:
    def __init__(self, node_name: str, parent, children):
        self.current = node_name
        self.children = children
        self.earned = 0
        self.parent = parent
    def add_child(self, node):
        self.children.append(node)

    def earn(self, amt):
        if self.current != None and amt > 0:
            tmp_earn = int(math.ceil(amt*0.9))
            self.earned += tmp_earn
            if self.parent is not None:
                self.parent.earn((amt - tmp_earn))


class Node:
    def __init__(self, node_name: str, parent: str, children: list[str]):
        self.current = node_name
        self.children = children
        self.earned = 0
        self.parent = parent

class Tree:
    def __init__(self):
        self._map = {}

    def add_node(self, target_node):
        self._map[target_node.current] = target_node
    def find_node(self, target_node_name) -> Node:
        return self._map[target_node_name]
    def earn(self, amt, target_node_name):
        tmp_earn = int(math.ceil(amt * 0.9))
        if target_node_name in self._map.keys() and amt >0:
            cur_node = self._map[target_node_name]
            cur_node.earned += tmp_earn
            self.earn(amt - tmp_earn, cur_node.parent)




class TreeLinkedList:
    def __init__(self, node):
        self.root = node
    def find_node(self, target_node_name: str):
        def _dfs(cur: Node):
            if cur.current == target_node_name:
                return cur
            final_node = None
            for child_node in cur.children:
                tmp = _dfs(child_node)
                if tmp is not None:
                    final_node = tmp
            return final_node
        return _dfs(self.root)

    def add_child(self, target_node, parent_node):
        parent_node.add_child(target_node)

def solutionOld(enroll, referral, seller, amount):
    atree = build_tree(enroll, referral)
    for amt, a_seller in zip([100*e for e in amount], seller):
        target = atree.find_node(a_seller)
        target.earn(amt)
    result = []
    for e_id in enroll:
        target_node = atree.find_node(e_id)
        result.append(target_node.earned)
    return result


def solution(enroll, referral, seller, amount):
    atree = build_tree(enroll, referral)
    for amt, a_seller in zip([100*e for e in amount], seller):
        atree.earn(amt, a_seller)
    result = []
    for e_id in enroll:
        target_node = atree.find_node(e_id)
        result.append(target_node.earned)
    return result

def build_tree(enroll, referral):
    root_node = Node(node_name="center", children=[], parent=None)
    tree = Tree()
    tree.add_node(root_node)
    for parent, mem in zip(referral, enroll):
        if parent == "-":
            target = Node(node_name=mem, parent="center", children=[])
            tree.find_node("center").children.append(mem)
        else:
            target = Node(node_name=mem, parent=parent, children=[])
            tree.find_node(parent).children.append(mem)
        tree.add_node(target)
    return tree



def build_treeOld(e, r):
    root_node = Node(node_name="center", children=[], parent=None)
    atree = Tree(root_node)
    for parent, mem in zip(r,e):
        if parent == "-":
            root_node.add_child(Node(node_name=mem, children=[], parent=root_node))
        else:
            parent_node = atree.find_node(parent)
            child_node = Node(node_name=mem, children=[], parent=parent_node)
            atree.add_child(child_node, parent_node)
    return atree

if __name__ == "__main__":
    e = ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]
    ref = ["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]
    # seller = ["young", "john", "tod", "emily", "mary"]
    seller = ["sam", "emily", "jaimie", "edward"]
    # amt = [1200, 400, 200, 500, 1000]
    amt = [2, 3, 5, 4]

    print(solution(e, ref, seller, amt))

