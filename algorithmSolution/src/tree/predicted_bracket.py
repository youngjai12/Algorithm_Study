# https://school.programmers.co.kr/learn/courses/30/lessons/12985
#   1. 처음에는 half기준으로 같은 방향에 있으면 대진이 일어난다고 생각. 계속 다른방향이다가 처음 깉은방향이면...
#   2. 1과는 반대로 생각해서, 처음 쪼개질때 그떄가 대진이 일어나는 것이다. -> 같은방향이면 +1씩하다가 전체 depth에서 빼면될듯
#   주의해야할것
#    - st,end 경계 처리하는것. (target<=end 이어도 포함이엇으므로, 등호가 이하에 붙는게 맞앗음)

def get_rounds(n):
    def _dfs2(cc):
        if cc == 1:
            return 1
        return _dfs2(cc/2)+1
    return _dfs2(n)-1




def solution(n, a, b):
    def _dfs(cur, a1, b1, cnt):
        half = cur / 2
        if a1 < half and b1 < half:
            return cnt
        elif a1 >= half and b1 >= half:
            return cnt
        elif a1 >= half:
            _dfs((half + cur)/2, a1, b1, cnt+1)
        elif a1 < half:
            _dfs(half/2, a1, b1, cnt+1)
        if cur<=1 or cur >= n:
            return cnt

    def _dfs2(st, end, a1, b1, cnt):
        half = int((st +end) / 2)
        if half == 1 or half == n:
            return cnt
        if (a1 <= half and b1 > half) or (a1 > half and b1 <= half):
            return cnt
        else:
            if a1 > half:
                return _dfs2(half, end, a1, b1, cnt+1)
            else:
                return _dfs2(st, half, a1, b1, cnt + 1)

    ccnt = _dfs2(1, n, a, b, 0)
    return get_rounds(n) - ccnt




if __name__ == "__main__":
    print(solution(8,4,7))