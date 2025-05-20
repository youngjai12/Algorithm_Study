# https://school.programmers.co.kr/learn/courses/30/lessons/12973

def solution(s):
    answer = []
    for ss in s:
        if len(answer) == 0 or answer[-1]!=ss:
            answer.append(ss)
        else:
            answer.pop(-1)
    if answer == 0:
        return 1
    else:
        return 0

if __name__ == '__main__':
    target_str = "baabaa"
    solution(target_str)