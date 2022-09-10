
class Solution {

    private static final String[] initials = new String[] { "A", "C", "F", "J", "M", "N", "R", "T" };
    private static final boolean[] visited = new boolean[8];
    private static final String[] picked = new String[8];
    private int count = 0;

    public int solution(int n, String[] data) {
        perm(visited, 8, picked, data);
        return count;
    }

    private void perm(boolean[] visited, int r, String[] picked, String[] data) {
        if (r == 0) {
            String s = String.join("", picked);
            if (analysisData(s, data)) {
                count++;
            }
            return;
        }

        for (int i = 0; i < initials.length; ++i) {
            if (visited[i]) {
                continue;
            }
            picked[initials.length - r] = initials[i];
            visited[i] = true;
            perm(visited, r - 1, picked, data);
            visited[i] = false;
        }
    }

    private boolean analysisData(String picked, String[] data) {
        for (String condition : data) {
            int startIndex = picked.indexOf(condition.charAt(0));
            int endIndex = picked.indexOf(condition.charAt(2));
            char op = condition.charAt(3);
            int distance = condition.charAt(4) - '0';

            if (op == '=' && Math.abs(startIndex - endIndex) - 1 != distance) {
                return false;
            } else if (op == '<' && !(Math.abs(startIndex - endIndex) - 1 < distance)) {
                return false;
            } else if (op == '>' && !(Math.abs(startIndex - endIndex) - 1 > distance)) {
                return false;
            }
        }
        return true;
    }

}
