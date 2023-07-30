def make_file(file):
    header = ""
    number = 0
    idx = 0
    for i in range(len(file)):
        if str.isdigit(file[i]):
            header = file[:i]
            idx = i
            break
    for i in range(idx, len(file)):
        if not str.isdigit(file[i]):
            number = file[idx:i]
            idx = i
            break
        if i == len(file) - 1:
            number = file[idx:i+1]
            idx = i + 1
    return (header, number, file[idx:])


def solution(files):
    file_formats = list(map(lambda x: make_file(x), files))
    file_formats.sort(key=lambda x:(x[0].lower(), int(x[1])))
    return ["".join(file_format) for file_format in file_formats]
   