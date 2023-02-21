import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    public String solution(String s) {
        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(s);
        return matcher.replaceAll(matchResult -> {
            String match = matchResult.group();
            return match.substring(0, 1).toUpperCase() + match.substring(1).toLowerCase();
        });
    }
}