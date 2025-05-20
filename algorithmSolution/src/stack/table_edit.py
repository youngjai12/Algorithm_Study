# https://school.programmers.co.kr/learn/courses/30/lessons/81303

# 중간중간 빠진 element를 고려해서 cur_loc을 tracking하는게 어렵다
# 반례 어떻게 찾지


def get_next_loc(dir, amt, target_arr, cur):
    step = 0
    while step < amt:
        if dir == "D":
            cur += 1
            if target_arr[cur]:
                cur += 1
        else:
            cur -=1
            if target_arr[cur]:
                cur -=1
        step +=1
    return cur



def solution(n, k, cmd):
    cancel_stack = []
    target_arr = [False] * n
    tot_len = n
    cur_loc = k
    for a_cmd in cmd:
        tmp = a_cmd[0]
        if tmp in ["D", "U"]:
            amt = int(a_cmd.split(" ")[1])
            cur_loc = get_next_loc(tmp, amt, target_arr, cur_loc)

        elif tmp == "C":
            cancel_stack.append(cur_loc)
            target_arr[cur_loc] = True
            if cur_loc != tot_len-1:
                cur_loc += 1
            elif cur_loc > 0:
                cur_loc -=1
            tot_len -= 1
            print(f"{target_arr} at({cur_loc})")
        else:
            if len(cancel_stack) > 0:
                to_pop = cancel_stack.pop(-1)
                target_arr[to_pop] = False
                tot_len +=1
    answer = ""
    for e in target_arr:
        if not e:
            answer+="O"
        else:
            answer+="X"
    return answer



if __name__ == "__main__":

    #cmd = ["D 2","C","U 3","C","D 4","C","U 2","Z","Z"];n=3;k=2
    #cmd = ["C", "U 4", "U 5", "Z", "C", "C", "C", "C", "C", "Z"];n=20;k=15

    # cmd = ["C", "C", "C", "Z", "Z", "C"];n=3;k=2
    # cmd = ["D 2", "C", "Z", "U 2", "C"];n=5; k=1
    #cmd = ["D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"];n=3;k=2
    # print(solution(n, k, cmd))
    dd = []
    dd.pop(-1)



