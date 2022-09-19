parent = []
money = []


def update(x, val):
    """
    노드와 그 부모의 돈을 업데이트
    
    Args:
      x: 우리가 현재 있는 노드
      val: 그 사람이 가지고 있는 돈의 양
    """
    while x != parent[x] and val:
        parent_money = val // 10
        my_money = val - parent_money
        money[x] += my_money
        x = parent[x]
        val = parent_money



def solution(enroll, referral, seller, amount):
    """
    등록 목록, 추천 목록, 판매자 목록 및 금액 목록을 가져와 각 사람이 만든 금액 목록을 반환
    
    Args:
      enroll: ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]
      referral: ["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]
      seller: ["young", "john", "tod", "emily", "mary"]
      amount: [12, 4, 2, 5, 10]
    
    Returns:
      [360, 958, 108, 0, 450, 18, 180, 1080]
    """
    global parent, money
    parent = list(i for i in range(len(enroll) + 1))
    money = [0] * (len(enroll) + 1)
    name_index = {v: i + 1 for i, v in enumerate(enroll)}
    name_index["-"] = 0
    for i, rel in enumerate(referral):
        parent[i + 1] = name_index[rel]
    for p, beneafit in zip(seller, amount):
        update(name_index[p], beneafit*100)
    return money[1:]





print(
    solution(
        ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"],
        ["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"],
        ["young", "john", "tod", "emily", "mary"],
        [12, 4, 2, 5, 10],
    )
)
