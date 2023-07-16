import re


def solution(skill, skill_trees):
    count = 0
    for tree in skill_trees:
        replaced = re.sub(f"[^{skill}]", "", tree)
        if skill.startswith(replaced):
            count += 1
    return count