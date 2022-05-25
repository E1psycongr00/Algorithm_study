expression = input()

def tokenize(ss:str):
    ret = []
    tmp = []
    for s in ss:
        if s.isdigit():
            tmp.append(s)
        else:
            ret.append("".join(tmp))
            tmp.clear()
            ret.append(s)
    if tmp:
        ret.append("".join(tmp))
    return ret

expression = tokenize(expression)
status = 0
buffer = []
result = 0
for s in expression:
    if status == 0 and s == '-':
        status = 1
    elif str.isdigit(s) and status == 1:
        buffer.append(int(s))
    elif status == 1 and s == '-':
        result -= sum(buffer)
        buffer.clear()
    elif str.isdigit(s):
        result += int(s)

if buffer and status == 1:
    result -= sum(buffer)

print(result)