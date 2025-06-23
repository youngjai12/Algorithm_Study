# https://school.programmers.co.kr/learn/courses/30/lessons/1845



def solution1(nums):
    uniq_cnt = len(set(nums))
    return min(uniq_cnt, int(len(nums)/2))

def solution2(nums):
