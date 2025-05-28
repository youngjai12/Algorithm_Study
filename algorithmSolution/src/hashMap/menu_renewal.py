# https://school.programmers.co.kr/learn/courses/30/lessons/72411
# 10C5 -> 10k x 원소갯수가 10~20개.

from collections import defaultdict

def get_comb(target_str, cnt):
    size = len(target_str)
    tot_list=[]
    def _dfs(st_idx, cur_str, cur_cnt):
        if cur_cnt == cnt:
            tot_list.append(cur_str)
        else:
            for i in range(st_idx, size):
                _dfs(i+1, cur_str+target_str[i], cur_cnt+1)

    _dfs(0, "", 0)
    return tot_list


def solution(orders, course):
    cnt_map = defaultdict(int)
    sorted_orders = ["".join(sorted(e)) for e in orders]
    for aword in sorted_orders:
        for llen in course:
            combs = get_comb(aword, llen)
            for comb in combs:
                cnt_map[comb] += 1
    answer = []
    for c in course:
        tmp = {comb:cnt for comb, cnt in cnt_map.items() if len(comb) == c}
        if len(tmp) == 0:
            continue
        max_cnt = max(tmp.values())
        if max_cnt > 1:
            answer.extend([k for k,v in tmp.items() if v == max_cnt])
    return sorted(answer)




if __name__ == '__main__':
    #orders = ["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"]
    # orders = ["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"]
    orders = ["XYZ", "XWY", "WXA"]
    course = [2, 3, 4]
    print(solution(orders, course))

    #print(get_comb("ABCFG", 3))
