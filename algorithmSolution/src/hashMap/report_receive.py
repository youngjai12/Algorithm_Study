# https://school.programmers.co.kr/learn/courses/30/lessons/92334
from collections import defaultdict, Counter


def solution(id_list, report, k):
    reportee_map = defaultdict(set)
    for rept in report:
        reporter, abuser = rept.split(' ')
        reportee_map[abuser].add(reporter)
    result = []
    for abuser, reporters in reportee_map.items():
        if len(reporters) >= k:
            result.extend(reporters)
    answer = Counter(result)
    return [answer[idd] for idd in id_list]



if __name__ == '__main__':
    # id_list = ["muzi", "frodo", "apeach", "neo"]
    id_list = ["con", "ryan"]
    # report = ["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]
    report = ["ryan con", "ryan con", "ryan con", "ryan con"]
    print(solution(id_list, report, 3))