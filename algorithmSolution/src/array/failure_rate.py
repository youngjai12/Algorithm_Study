# https://school.programmers.co.kr/learn/courses/30/lessons/42889
from collections import Counter, defaultdict

# 런타임 오류 발생코드
def solution1(N, stages):
    cnt_info = Counter(stages)
    stage_enter_cnt = defaultdict(int)

    cnt_info[0] = len(stages)
    stage_enter_cnt[max(stages)] = cnt_info[max(stages)]

    for stage in range(N, 0, -1):
        stage_enter_cnt[stage] = stage_enter_cnt[stage+1] + cnt_info[stage]

    result = []
    for stage in range(1, N+1):
        result.append((stage, cnt_info[stage] / stage_enter_cnt[stage]))

    dd = sorted(result, key=lambda x: (x[1], -x[0]), reverse=True)
    return [e[0] for e in dd]


def solution(N, stages):
    cnt_info = Counter(stages)
    stage_enter_cnt = defaultdict(int)

    cnt_info[0] = len(stages)
    stage_enter_cnt[max(stages)] = cnt_info[max(stages)]

    for stage in range(N, 0, -1):
        stage_enter_cnt[stage] = stage_enter_cnt[stage+1] + cnt_info[stage]

    result = []
    for stage in range(1, N+1):
        if stage_enter_cnt[stage] == 0:
            result.append((stage, 0))
        else:
            result.append((stage, cnt_info[stage] / stage_enter_cnt[stage]))

    dd = sorted(result, key=lambda x: (x[1], -x[0]), reverse=True)
    return [e[0] for e in dd]


if __name__ == '__main__':
    N = 5
    stages = [2,3]
    print(solution(N, stages))
