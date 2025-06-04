# 918. Maximum Sum Circular Subarray
# 부분합 문제랑 거의 비슷한데, 다만 circular 구조라는 정도..!
# 원래배열 : 1, -2, 3, 5, -4, 2, 5
# index합 : 1, -1, 3, 8, 4, 6, 11

def maxSubarraySumCircular(nums: list[int]):
    