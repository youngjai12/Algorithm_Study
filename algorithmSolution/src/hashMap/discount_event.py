# https://school.programmers.co.kr/learn/courses/30/lessons/131127
# 어렵진 않았는데, range문 reverse를 착각해서..
# 10일 뒤에것은 사라진다는것도 고려 잘 햇어야함. 

from collections import defaultdict

def solution(want, number, discount):
    mmap = defaultdict(int)
    def _order_req():
        answer = {}
        for product, cnt in zip(want, number):
            answer[product] = cnt
        return answer

    wwant = _order_req()
    cnt = 0
    for idx in range(len(discount)-1, -1, -1):
        mmap[discount[idx]] += 1
        if idx + 10 < len(discount):
            mmap[discount[idx+10]] -= 1
        flag = True
        for k, v in wwant.items():
            if mmap[k] < v:
                flag=False
                break
        if flag:
            cnt +=1
    return cnt





if __name__ == '__main__':
    wants = ["banana", "apple", "rice", "pork", "pot"]
    wants =["a", "b"]
    # number = [3,2,2,2,1]
    number = [1, 9]
    # discount = ["chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"]
    discount = ["b", "b", "b", "b", "b", "b", "b", "b", "b", "b", "a"]
    print(solution(wants, number, discount))


