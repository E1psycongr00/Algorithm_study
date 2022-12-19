from typing import List


def solution(phone_book:List[str]):
    phone_book.sort()
    for p1, p2 in zip(phone_book, phone_book[1:]):
        if p2.startswith(p1):
            return False
    return True

print(sorted(["1", "21", "2", "12", "123"]))