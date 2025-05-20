# https://school.programmers.co.kr/learn/courses/30/lessons/76502


def is_valid(target_str):
    sstack = []
    for target_char in target_str:
        if target_char in ["[", "{", "("]:
            sstack.append(target_char)
        else:
            if len(sstack) == 0:
                return False
            if target_char == "]" and sstack[-1] == "[":
                sstack.pop()
            if target_char == "}" and sstack[-1] == "{":
                sstack.pop()
            if target_char == ")" and sstack[-1] == "(":
                sstack.pop()

    return len(sstack) == 0


def solution(target_str):
    cnt =0
    for i in range(len(target_str)):
        if is_valid(f"{target_str[i:]}{target_str[0:i]}"):
            cnt += 1
    return cnt