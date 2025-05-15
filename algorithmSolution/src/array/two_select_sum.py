# https://school.programmers.co.kr/learn/courses/30/lessons/68644

# 두개 뽑아서 더하기 : 무난..

def solution(numbers):
    answer = set()
    for i in range(len(numbers)):
        for j in range(i+1, len(numbers)):
            tmp = numbers[i] + numbers[j]
            answer.add(tmp)
    sorted_list = sorted(list(answer))
    return sorted_list


if __name__ == '__main__':
    targets = [2,1,3,4,1]
    #targets = [5,0,2,7]
    print(solution(targets))