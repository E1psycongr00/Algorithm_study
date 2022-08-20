
import java.util.Arrays;
import java.util.HashSet;


interface SkillTreeChecker {

    boolean isValidSkill(String skill);
}

class DefaultSkillTreeCheckerImpl implements SkillTreeChecker {

    private static final int NOT_FOUND = -1;
    private static final int START_INDEX = 0;
    private final String skill;
    private final HashSet<Character> visited = new HashSet<>();

    public DefaultSkillTreeCheckerImpl(String skill) {
        this.skill = skill;
    }

    @Override
    public boolean isValidSkill(String skillTree) {
        for (char ch : skillTree.toCharArray()) {
            if (!isValidOrderSkill(ch)) {
                visited.clear();
                return false;
            }
        }
        visited.clear();
        return true;
    }


    private boolean isValidOrderSkill(char ch) {
        int idx = skill.indexOf(ch);
        if (idx == NOT_FOUND) {
            return true;
        }
        if (idx == START_INDEX) {
            visited.add(ch);
            return true;
        }

        if (visited.contains(skill.charAt(idx - 1))) {
            visited.add(ch);
            return true;
        }
        return false;
    }
}

class Solution {
    public int solution(String skill, String[] skill_trees) {
        SkillTreeChecker skillTreeChecker = new DefaultSkillTreeCheckerImpl(skill);
        return (int) Arrays.stream(skill_trees).filter(skillTreeChecker::isValidSkill).count();
    }
}