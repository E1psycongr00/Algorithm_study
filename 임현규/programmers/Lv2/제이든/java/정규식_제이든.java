public class Solution {

    private final Pattern pattern = Pattern.compile("\\b[a-zA-Z]+\\b");

    public String solution(String letter) {
        String lower = letter.toLowerCase();
        Matcher matcher = pattern.matcher(lower);
        return matcher.replaceAll(matchResult -> {
            String matched = matchResult.group();
            return Character.toUpperCase(matched.charAt(0)) + matched.substring(1);
        });
    }
}