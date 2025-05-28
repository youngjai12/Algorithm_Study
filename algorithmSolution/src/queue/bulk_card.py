# https://school.programmers.co.kr/learn/courses/30/lessons/159994
# 큐를 활용하지 않았음.
# 접근은 쉽게했으나, 문제조건("카드를 사용하지않고 다음카드로 넘어갈수 없다")을 잘안읽어서..



def is_valid(target):
    return target == [e for e in range(len(target))]


def solution(cards1, cards2, goal):
    order_card1 = {w: idx for idx, w in enumerate(cards1)}
    order_card2 = {w: idx for idx, w in enumerate(cards2)}

    card_pile1, card_pile2 = [], []

    for word in goal:
        if word in cards1:
            card_pile1.append(order_card1[word])
        elif word in cards2:
            card_pile2.append(order_card2[word])
        else:
            return False

    return is_valid(card_pile1) and is_valid(card_pile2)


if __name__ == '__main__':
    # cards1 = ["i", "drink", "water"]; cards2 = ["want", "to"]; goal = ["i", "want", "to", "drink", "water"]
    # cards1 = ["i", "water", "drink"]; cards2 = ["want", "to"]; goal=["i", "want", "to", "drink", "water"]
    cards1=["i", "see", "to"]; cards2=["you", "now", "me"];goal=["i", "see", "now", "me"]
    # cards1=['c','f']; cards2=['d','a','b']; goal=['d','a','b']
    print(solution(cards1, cards2, goal))

