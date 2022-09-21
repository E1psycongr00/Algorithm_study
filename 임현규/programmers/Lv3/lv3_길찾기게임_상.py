""" 트리에 익숙하다면 어려운 문제는 아니다. 트리의 추가 방식을 재귀를 이용해 적용해본다.
"""

from collections import defaultdict
from dataclasses import dataclass
import sys

sys.setrecursionlimit(10**6)


@dataclass
class Node:
    num: int
    x: int
    y: int
    left = None
    right = None


def add_node(parent, child):
    """
    자식의 x 값이 부모의 x 값보다 작으면 자식을 부모의 왼쪽에 추가합니다. 그렇지 않으면 부모의 오른쪽에 자식을 추가

    Args:
      parent: 부모 노드
      child: 추가할 노드
    """
    if child.x < parent.x:
        if parent.left is None:
            parent.left = child
        else:
            add_node(parent.left, child)
    elif child.x > parent.x:
        if parent.right is None:
            parent.right = child
        else:
            add_node(parent.right, child)


def preorder(ans, node):
    if node is None:
        return
    ans.append(node.num)
    preorder(ans, node.left)
    preorder(ans, node.right)


def postorder(ans, node):
    if node is None:
        return
    postorder(ans, node.left)
    postorder(ans, node.right)
    ans.append(node.num)


def solution(nodeinfo):
    nodes = [Node(i + 1, x, y) for i, (x, y) in enumerate(nodeinfo)]
    nodes.sort(key=lambda node: (-node.y, node.x))
    root = nodes[0]
    for i in range(1, len(nodes)):
        add_node(root, nodes[i])

    answer = [[], []]
    preorder(answer[0], root)
    postorder(answer[1], root)
    return answer


print(
    solution([[5, 3], [11, 5], [13, 3], [3, 5], [6, 1], [1, 3], [8, 6], [7, 2], [2, 2]])
)
