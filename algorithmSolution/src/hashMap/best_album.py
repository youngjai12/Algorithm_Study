# https://school.programmers.co.kr/learn/courses/30/lessons/42579


from collections import defaultdict

def solution(genres, plays):
    play_map = defaultdict(list)
    sum_map=defaultdict(int)
    for idx, (g, p) in enumerate(zip(genres, plays)):
        play_map[g].append((idx, p))
        sum_map[g]+=p

    ordered_genres = sorted(sum_map.items(), key=lambda x: x[1], reverse=True)
    final_ranks = []
    for e in ordered_genres:
        gen = e[0]
        user_info_list = play_map[gen]
        sorted_user_info = sorted(user_info_list, key=lambda x: x[1], reverse=True)
        for i in sorted_user_info[:2]:
            final_ranks.append(i[0])
    return final_ranks

