# https://school.programmers.co.kr/learn/courses/30/lessons/42576



def solution(participant, completion):
    p1 = sorted(participant)
    c1 = sorted(completion)
    idx = 0
    while idx < len(c1):
        if p1[idx] != c1[idx]:
            return p1[idx]
        idx+=1
    return p1[-1]

import collections

def solution2(participant, completion):
    p1 = collections.Counter(participant)
    c1 = collections.Counter(completion)
    for name,cnt in p1.items():
        if name not in c1.keys() or c1[name] != cnt:
            return name


if __name__ == '__main__':
    p = ["marina", "josipa", "nikola", "vinko", "filipa"]
    c = ["josipa", "filipa", "marina", "nikola"]
    #print(solution(p, c))
    print(solution2(p, c))