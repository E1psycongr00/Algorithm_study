"""
    까다로운 정규식 문제이다. 문제 풀이는 다음과 같다.
    1. url을 찾는 정규식은 패턴 탐색을 괄호로 그룹짓고 그 결과 값을 활용한다. ex) group(1)
    2. 매칭되는 글자의 경우 숫자 또는 빈칸 경계시 찾는 문제이다. 2가지 방법이 있다.
        i. [a-z]+ 글자를 찾고 찾은 글자 == word면 카운트한다.
        ii. 숫자를 빈칸으로 치환하고 \\bword\\b 를 활용해 찾는다.
    3. link를 찾는다. 1번과 비슷한 패턴을 사용하고 url에 빈칸이 있으면 안되므로 \\S 를 활용한다.
    4. graph를 만들어서 링크간의 연결 관계를 만들어준다.
    5. 매칭 점수를 구한다.
"""
from collections import defaultdict
import re


def find_url(page):
    url_pattern = '<meta property="og:url" content="(\S+)"'
    match_result = re.search(url_pattern, page)
    return match_result.group(1)


def find_num_of_word(page, word:str):
    page = re.sub("\\d+", " ", page)
    return len(re.findall("\\b"+word+"\\b", page))
    # count = 0
    # for w in re.findall("[a-z]+", page):
    #     if w == word:
    #         count += 1
    # return count


def find_connect_link(page):
    link_pattern = 'a href="(\S+)"'
    result = re.findall(link_pattern, page)
    return result


def solution(word, pages):
    url_index = {}
    pages = list(map(str.lower, pages))
    word = str.lower(word)
    common_scores = []
    num_of_link = []
    graph = defaultdict(list)
    link_scores = []
    for i, page in enumerate(pages):
        url = find_url(page)
        url_index[url] = i

    for i, page in enumerate(pages):
        common_scores.append(find_num_of_word(page, word))
        links = find_connect_link(page)
        for link in links:
            if link in url_index:
                graph[url_index[link]].append(i)
        num_of_link.append(len(links))

    for i in range(len(pages)):
        link_score = sum([common_scores[j] / num_of_link[j] for j in graph[i]])
        link_scores.append(link_score)

    match_result = []
    for i in range(len(pages)):
        match_result.append(common_scores[i] + link_scores[i])
    
    return match_result.index(max(match_result))


print(
    solution(
        "Muzi",
        [
            '<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">\n<head>\n  <meta charset="utf-8">\n  <meta property="og:url" content="https://careers.kakao.com/interview/list"/>\n</head>  \n<body>\n<a href="https://programmers.co.kr/learn/courses/4673"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>',
            '<html lang="ko" xml:lang="ko" xmlns="http://www.w3.org/1999/xhtml">\n<head>\n  <meta charset="utf-8">\n  <meta property="og:url" content="https://www.kakaocorp.com"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href="https://hashcode.co.kr/tos"></a>\n\n\t^\n</body>\n</html>',
        ],
    )
)
