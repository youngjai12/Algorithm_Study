from collections import deque


# 84 Larget Rectanle in Histogram
# 어렵다고 느낀점
#   - 바로 전 아니라, 그 이전의 이전까지도 고려해야하는 경우가 생길것 같음 -> 그래서 이걸 stack에 넣으면서 tracking한다.
#   - 새로들어오는 height에 대해서 이전보다 낮다 -> 이것기준으로 더 못쌓으니까, 이거랑 높이 똑같은것까지 뺀다.
#        => [3,5,6,7]에 4들어오면 3전까지는 계산할필요도 없다는..이걸 stack에 넣으면서 바로바로 계산
#   - 새로들어온 height > 이전 -> 그대로 쌓는댜(stack)
# 제출후 맞닥뜨린 어려운점
#   - [3,2,4] -> 3을 pop()하더라도 2만큼의 높이는 됐었다는 사실을 고려해야함.
# [4,5,3,2] -> 이런 경우일때, 3으로해서 다 stack에서 제외해버리는 경우가 생김.. 2로 또 4,5,3부터 돌아야하는데..
# 대소비교 끝나고 남은것을 stack에 넣어줄때 큰 차이가 있었음.. => 그 자리를 넣는게 아니라, 앞으로 당겨질수잇는 idx를 넣는다...!

def calc(h_stack, given):
    first_x = min([e[1] for e in h_stack])
    ans = (given[1]-first_x+1) * given[0]
    for i in h_stack:
        area = (h_stack[0][1]-i[1]+1) * i[0]
        ans = max(ans, area)
    return ans

def largestRectangleArea_sol1(heights: list[int]):
    sstack = deque()

    sstack.append((heights[0], 0))
    ans = 0
    for idx, h in enumerate(heights[1:]):
        if sstack[-1][0] <= h:
            sstack.append((h, idx+1))
        else:
            to_calc = deque()
            first_idx = len(heights)
            while sstack and sstack[-1][0] > h:
                tmp = sstack.pop()
                first_idx=min(first_idx, tmp[1])
                to_calc.append(tmp)
            ans = max(ans, calc(to_calc, (h, idx+1)))
            llidx = first_idx
            if idx == len(heights)-2:
                llidx = len(heights)-1
            sstack.append((h, llidx))

    to_calc = deque()
    while sstack:
        tmp = sstack.pop()
        to_calc.append(tmp)
    ans = max(ans, calc(to_calc, to_calc[-1]))

    return ans

def largestRectangleArea(heights: list[int]):
    sstack = deque()
    ans = 0

    for idx, h in enumerate(heights):
        while sstack and sstack[-1][1] > h:
            loc, height = sstack.pop()
            area = (idx-loc) * height
            ans = max(ans, area)
        sstack.append((idx, h))


    while sstack:
        loc, h  = sstack.pop()
        tt_area = h * (len(heights)-loc)
        ans = max(ans, tt_area)
    return ans

def sol2(heights):
    stack = []
    max_v = 0

    for i, h in enumerate(heights):
        idx = i
        # 스택에 값이 존재하면 큰 값을 만날 때까지 pop
        while stack and stack[-1][1] > h:
            idx, height = stack.pop()
            rst = (i - idx) * height
            max_v = max(max_v, rst)
        stack.append([idx, h])

    # 전체가 동일한 높이일 경우를 추가적으로 고려
    while stack:
        idx, height = stack.pop()
        rst = (len(heights) - idx) * height
        max_v = max(max_v, rst)

    return max_v


if __name__ == "__main__":
    target_heights = [3,2,3]
    # target_heights = [0,1,0,2,1,0,1,3,2,1,2,1]
    #target_heights = [6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3]
    #target_heights = [2, 1, 5, 6, 2, 3]
    print(largestRectangleArea(target_heights))
    print(sol2(target_heights))
