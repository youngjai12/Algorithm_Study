# 주식 가격 : https://school.programmers.co.kr/learn/courses/30/lessons/42584


def solution(prices):
    answer = [0]*len(prices)
    for i in range(len(prices)-1):
        cnt=0
        for j in range(i+1, len(prices)):
            if prices[i] <= prices[j]:
                cnt+=1
            else:
                cnt+=1
                break
        answer[i] = cnt
    return answer

