import re


def solution(files):
    file_info_list = make_file_info_list(files)
    return list(map(lambda x: x[3], sorted(file_info_list, key=lambda x: (x[0], x[1]))))


def make_file_info_list(files):
    result = []
    
    for file in files:
        match_result = re.search("\\d+", file).span()
        head = file[:match_result[0]]
        number = file[match_result[0]: match_result[1]]
        tail = file[match_result[1]:]
        
        result.append((str.lower(head), int(number), tail, file))
    return result