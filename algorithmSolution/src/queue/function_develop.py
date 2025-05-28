# https://school.programmers.co.kr/learn/courses/30/lessons/42586
import math

def solution(progresses, speeds):
    remnant_days = [math.ceil((100-progresses[idx]) / speeds[idx]) for idx in range(len(progresses))]
    stack = [remnant_days[0]]
    answer = []
    for day in remnant_days[1:]:
        cnt = 0
        while stack and stack[-1] < day:
            stack.pop()
            cnt +=1
        stack.append(day)
        if cnt > 0:
            answer.append(cnt)
    answer.append(len(stack))

    return answer



if __name__ == '__main__':
    # prog = [95, 90, 99, 99, 80, 99]
    prog = [93, 30, 55]
    # speeds = [1, 1, 1, 1, 1, 1]
    speeds = [1, 30, 5]
    print(solution(prog, speeds))