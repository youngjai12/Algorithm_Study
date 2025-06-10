# https://school.programmers.co.kr/learn/courses/30/lessons/43238
# ==== 왜 시간초과가 날까?
#  - 최대 인원이 10억임. -> 10억번 되나안되나 for문 같은거 돌면서 하면 10초 걸림
# 조건이 가능해지는 방향이 정해져있다. 라는 생각이들면 이분탐색 때린다. => 총 소요시간이 적어질수록 만족안할 경우가 증가하고, 총소요시간이 많아질수록 여유로워지니까
# 진짜 한끝차이로 문제 맞고 틀림...;; 경계조건을 잘 정하자.
#  1) while문 종료조건  2) end, st 를 mid 기준으로 어떤 값을 줄건지 => st를 (mid+1) 로 주는게 정석인듯..

def is_possible(times, tot_time, tot_person):
    return sum([int(tot_time / t) for t in times]) >= tot_person

def solution(n, times):
    gate_cnt = len(times)
    end = max(times) * int(n / gate_cnt)
    st = min(times) * int(n / gate_cnt)

    while st < end:
        mid = (st + end) // 2
        if is_possible(times, mid, n):
            end = mid
        else:
            st = mid+1
    if is_possible(times, st, n):
        return st
    return end


if __name__ == '__main__':
    n = 6
    times = [7, 10]
    print(solution(n, times))
    #is_possible(times, 27, 6)

