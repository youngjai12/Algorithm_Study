# https://school.programmers.co.kr/learn/courses/30/lessons/12981



def solution(n, words):
    def _sol():
        visited = set()
        for idx in range(0, len(words)-1):
            prev_word = words[idx]
            next_word = words[idx+1]
            visited.add(prev_word)
            if prev_word[-1] != next_word[0]:
                return ((idx+1)%n, (idx+1)//n)
            if next_word in visited:
                return ((idx+1)%n, (idx+1)//n)
    res = _sol()
    if res:
        return (res[0]+1, res[1]+1)
    return (0,0)



if __name__ == '__main__':
    #words = ["tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"]
    words = ["hello", "one", "even", "never", "now", "world", "draw"]
    n = 2
    print(solution(n, words))
