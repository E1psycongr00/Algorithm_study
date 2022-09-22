"""링크드 리스트를 활용하는 문제이다.
링크드 리스트의 재밌는 특징 중 하나는 바로 링크드 리스트는 삭제할때 삭제한 노드의 연결을 건드리는 것이 아니라
근처의 주변 노드가 관계를 끊고 연결한다.

ex) A - B - C

A.next = B
A.prev = null

B.next = C
B.prev = A

C.prev = B
C.next = null
==========================
A.next = C
A.prev = null

B.next = C
B.prev = A

C.prev = A
C.next = null

==========================

살펴보면 삭제된 B의 관계는 아무런 수정이 없고 주변 A, C만 수정됨을 알 수 있다. 이 삭제된 노드를 스택에 넣어서 최신 삭제된 순으로
꺼내주면 된다.
"""

from collections import deque
from dataclasses import dataclass
from typing_extensions import Self


@dataclass
class Node:
    data: int
    prev: Self
    next: Self

    def __init__(self, data, prev=None, next=None):
        self.data = data
        self.prev = prev
        self.next = next


def make_linked_list(n):
    """
    n개의 노드로 구성된 연결 목록을 생성하며, 각 노드는 목록에서 해당 인덱스 값을 가집니다.

    Args:
      n: 연결 리스트의 노드 수

    Returns:
      n개의 노드가 있는 연결 리스트.
    """
    root = node = Node(0)
    for i in range(1, n):
        node.next = Node(i)
        node.next.prev = node
        node = node.next
    return root


def up(node, d):
    for _ in range(d):
        node = node.prev
    return node


def down(node, d):
    for _ in range(d):
        node = node.next
    return node


def delete(node, st):
    """
    목록에서 노드를 삭제하고 다음 노드를 반환
    
    Args:
      node: 삭제할 노드
      st: 삭제된 노드 목록
    
    Returns:
      목록의 다음 노드
    """
    st.append(node)
    next_node = node.next
    prev_node = node.prev
    if prev_node is None:
        next_node.prev = prev_node
        return next_node
    if next_node is None:
        prev_node.next = next_node
        return prev_node
    prev_node.next = next_node
    next_node.prev = prev_node
    return next_node


def roll_back(st):
    """
    노드가 첫 번째 또는 마지막 노드가 아닌 경우 이전 및 다음 노드를 롤백하려는 노드에 다시 연결하기만 하면 됩니다.
    
    Args:
      st: 노드 스택
    
    Returns:
      스택에서 꺼낸 노드
    """
    node = st.pop()
    prev = node.prev
    next = node.next
    if prev is None:
        next.prev = node
        return
    if next is None:
        prev.next = node
        return
    prev.next = node
    next.prev = node


def find_header(node):
    prev = node.prev
    while prev:
        node = prev
        prev = node.prev
    return node


def command(node, s, st):
    comm = s.split(" ")
    print(comm)
    if comm[0] == "U":
        return up(node, int(comm[1]))
    if comm[0] == "D":
        return down(node, int(comm[1]))
    if comm[0] == "C":
        return delete(node, st)
    if comm[0] == "Z":
        roll_back(st)
        return node
    raise Exception("옳바르지 않은 형식")


def solution(n, k, cmd):
    root = make_linked_list(n)
    node = root
    history = []
    for _ in range(k):
        node = node.next
    for c in cmd:
        node = command(node, c, history)
    header = find_header(node)
    number = []
    while header:
        number.append(header.data)
        header = header.next
    answer = ["X"] * n
    for num in number:
        answer[num] = "O"
    return "".join(map(str, answer))


print(solution(8, 2, ["D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"]))
