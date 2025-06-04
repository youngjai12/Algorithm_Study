# https://school.programmers.co.kr/learn/courses/30/lessons/42586
# stack에 첫원소 넣을때, "while stack and (조건)" => 이런식으로 stack empty여부를 넣어주고 뒤에서 append
# 반례를 찾는데, local-minima에 빠져서


import math
from collections import deque


def solution(progresses, speeds):
    remnant_days = [math.ceil((100-progresses[idx]) / speeds[idx]) for idx in range(len(progresses))]
    stack = []
    answer = []
    for day in remnant_days[::-1]:
        cnt = 0
        if stack and stack[-1] > day:
            answer.append(len(stack))
            stack=[]
        stack.append(day)
        if cnt > 0:
            answer.append(cnt)
    answer.append(len(stack))

    return answer[::-1]

def solution2(progresses, speeds):
    remnant_days = [math.ceil((100 - progresses[idx]) / speeds[idx]) for idx in range(len(progresses))]
    mmax = 0
    end_pointer = 0
    st_pointer = 0
    answer = []
    while end_pointer < len(remnant_days):
        if mmax < remnant_days[end_pointer]:
            mmax = remnant_days[end_pointer]
            if end_pointer !=0:
                answer.append(end_pointer - st_pointer)
                st_pointer = end_pointer
        end_pointer += 1
    if end_pointer != st_pointer:
        answer.append(end_pointer - st_pointer)
    return answer






if __name__ == '__main__':
    #prog = [95, 90, 99, 99, 80, 99]
    prog = [93, 30, 55]
    # [6,3,10]
    #prog = [90, 98, 97, 96, 98]
    #speeds = [1, 1, 1, 1, 1, 1]
    speeds = [1, 30, 5]
    #speeds = [1,1,1,1,1]
    print(solution2(prog, speeds))
