# https://school.programmers.co.kr/learn/courses/30/lessons/42840
# 모의고사


def solution(answers):
    def _get_arr(target_ar, prob_cnt):
        remnant = prob_cnt % len(target_ar)
        return target_ar * (prob_cnt // len(target_ar)) + target_ar[:remnant]

    prob_cnt = len(answers)
    p1 = _get_arr([1,2,3,4,5], prob_cnt)
    p2 = _get_arr([2,1,2,3,2,4,2,5], prob_cnt)
    p3 = _get_arr([3, 3, 1, 1, 2, 2, 4, 4, 5, 5], prob_cnt)

    score1,score2,score3 = 0,0,0
    for iidx in range(len(answers)):
        answer = answers[iidx]
        if answer == p1[iidx]:
            score1 +=1
        if answer == p2[iidx]:
            score2 +=1
        if answer == p3[iidx]:
            score3 +=1

    mmax_score = max([score1, score2, score3])
    return [(ii+1) for ii, e in enumerate([score1, score2, score3]) if e == mmax_score]


if __name__ == '__main__':
    print(solution([1,2,3,4,5]))

