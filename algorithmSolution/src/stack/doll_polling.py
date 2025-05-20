# https://school.programmers.co.kr/learn/courses/30/lessons/64061



def solution(board:list[list[int]], moves:list[int]):

    size = len(board)
    arr = [[] for _ in range(size)]

    for i in range(size):
        for j in range(size):

            if board[i][j] != 0:
                arr[j].append(board[i][j])
    answer = []
    cnt = 0
    for m in moves:
        target_arr = arr[m-1]
        if len(target_arr) != 0:
            top_doll = target_arr.pop(0)
            if len(answer) == 0 or answer[-1] != top_doll:
                answer.append(top_doll)
            else:
                cnt += 2
                answer.pop(-1)
    return cnt




if __name__ == "__main__":
    target_board = [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]]
    target_moves = [1,5,3,5,1,2,1,4]
    print(solution(target_board, target_moves))