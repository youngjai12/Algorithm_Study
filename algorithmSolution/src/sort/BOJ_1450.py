# https://www.acmicpc.net/problem/1450
# 첫 트
#    - 너무 경우의 수가 많다... -> 그럼 이분탐색
#    - 구해내는 답을 max와 min을 좁혀가면서 푸는거아닌가...?
#    - 갈수있는 경우의수가 가능하다면 +, 아니면 lower-part search -> 근데 가능여부 구현이 복잡.
# 두 트
#   - 1) 일단 모든 조합을 고려해야하므로 30개로 고려하면 너무 많으니까 반으로 쪼갠다. 2^15
#   - 2) 어차피 합을 구해야하니까, 쪼갠 것에서 합에 대한 경우를 모두 구한다. 그럼 겹치는것도 있을텐데 이걸 줄세운다
#   - 3) 2)의 방식으로 각각 구하면, former는 작은것합, latter는 상대적으로 큰 수들의 합 이다. -> 그래서 pointer를 former는 큰것, latter는 작은것부터..
#   - 4) 여기서 투포인터로.. 왜냐면 합보다 작거나 같으면 right을 증가시키고, 아니면 left를 감소시키면 모든 경우 커버가능.
#   다만, 4)에서 합이 중복되면 여러번 list에 나오는데, right기준으로만 더해주고 있으니까, left의 중복을 더해주면 안됨..그래서 visit처리할까했는데.
#   그럼, 고려를 못하는 경우가 생겨버림. (right+=1 해주면 left의 값이 visited됐더라도 다시 더해줘야하는데..)

import sys
from collections import defaultdict
from itertools import combinations



def get_sum_info(half_list):
    dd = [0]
    dd.extend([sum(ee)  for i in range(1, len(half_list)+1) for ee in list(combinations(half_list, i))])
    tmp_list = sorted(dd)
    concat = defaultdict(int)
    for idx, e in enumerate(tmp_list):
        concat[e] = max(concat[e], idx)
    return concat

def get_sum_list(half_list):
    dd = [0]
    dd.extend([sum(ee)  for i in range(1, len(half_list)+1) for ee in list(combinations(half_list, i))])
    return sorted(dd)


def solve_tle(former_info, latter_list, target_sum):
    left_sum_list = list(former_info.keys())
    left_pointer = len(left_sum_list)-1

    right_pointer = 0
    answer = 0

    while left_pointer >= 0 and right_pointer < len(latter_list):
        left_sum = left_sum_list[left_pointer]
        if left_sum > target_sum:
            left_pointer -= 1
        else:
            tmp_sum = left_sum + latter_list[right_pointer]

            if tmp_sum <= target_sum:
                answer += (former_info[left_sum] + 1)
                right_pointer += 1

            elif tmp_sum > target_sum:
                left_pointer -=1

    return answer




if __name__ == '__main__':
    input = sys.stdin.readline
    cnt, max_weight = map(int, input().split())
    weights = list(map(int, input().split()))
    list1 = get_sum_info(weights[:(cnt//2)])
    list2 = get_sum_list(weights[(cnt // 2):])

    print(solve_tle(list1, list2, max_weight))

