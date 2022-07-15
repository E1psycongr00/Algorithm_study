class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        s = " " + s.toLowerCase();
        for (int i = 1; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (s.charAt(i-1) == ' ' && Character.isAlphabetic(ch)) {
                ch = Character.toUpperCase(ch);
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}