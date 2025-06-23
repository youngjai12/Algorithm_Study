# https://school.programmers.co.kr/learn/courses/30/lessons/42577
# 시간초과(solution_TLE)
#   - 탐색할 필요가 없는 경우를 더 탐색하나...? (어차피 pstn의 길이가 적은것부터 순서로 탐색해서...)
#   -


from collections import defaultdict


def solution(phone_book):
    phone_book.sort()
    for i, j in zip(phone_book, phone_book[1:]):
        if j.startswith(i):
            return False
    return True


def solution_TLE(pstn_list):
    info_map = defaultdict(list)
    for pstn in pstn_list:
        info_map[len(pstn)].append(pstn)

    pstn_len_list = sorted(list(info_map.keys()))
    def _is_matched(shorter_list, longer_list):
        for pstn in longer_list:
            for prefix in shorter_list:
                if pstn.startswith(prefix):
                    return False
        return True

    for idx in range(len(pstn_len_list)-1):
        a_list = info_map[pstn_len_list[idx]]
        for sub_idx in range(idx+1, len(pstn_len_list)):
            b_list = info_map[pstn_len_list[sub_idx]]
            if not _is_matched(a_list, b_list):
                return False
    return True

if __name__ == '__main__':
    #pstn_list = ["119", "97674223", "1195524421"]
    pstn_list = ["12","123","1235","567","88"]
    #pstn_list = ["123","456","789"]
    print(solution(pstn_list))

