# https://school.programmers.co.kr/learn/courses/30/lessons/42888
# 거의 구현문제임..
from collections import defaultdict


def solution(record):
    answer = []
    name_tag = defaultdict(str)
    for rec in record:
        tmp = rec.split(" ")
        if tmp[0] == "Enter":
            answer.append((tmp[1], "님이 들어왔습니다."))
            name_tag[tmp[1]] = tmp[2]
        elif tmp[0] == "Leave":
            answer.append((tmp[1], "님이 나갔습니다."))
        elif tmp[0] == "Change":
            name_tag[tmp[1]] = tmp[2]
    return answer, name_tag

if __name__ == '__main__':
    target = ["Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo", "Change uid4567 Ryan"]
    ttmp, name_map = solution(target)
    print(name_map)
    for rec in ttmp:
        print(name_map[rec[0]]+rec[1])

    # print(solution(target))
