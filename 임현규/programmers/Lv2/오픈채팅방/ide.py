nicknames = {}

ENTER = "{nick}님이 들어왔습니다."
LEAVE = "{nick}님이 나갔습니다."

def solution(record):
    logs = []
    for s in record:
        split = s.split(" ")
        if split[0] == "Enter":
            logs.append((ENTER, split[1]))
            nicknames[split[1]] = split[2]
        elif split[0] == "Leave":
            logs.append((LEAVE, split[1]))
        elif split[0] == "Change":
            nicknames[split[1]] = split[2]
    return [log.format(nick=nicknames[id]) for log, id in logs]
        