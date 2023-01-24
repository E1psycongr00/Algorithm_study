import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    private static final String[] words = new String[]{"aya", "ye", "woo", "ma"};
    private static final Pattern pattern = Pattern.compile("[a-zA-Z]+");

    public int solution(String[] babbling) {
        int count =  0;
        for (String bab : babbling) {
            String encoded = encode(bab);
            if (isFullEncoded(encoded) && isNotAdjacentDuplicate(encoded)) {
                count++;
            }
        }
        return count;
    }

    private boolean isFullEncoded(String input) {
        Matcher matcher = pattern.matcher(input);
        return !matcher.find();
    }

    private boolean isNotAdjacentDuplicate(String input) {
        for (int i = 0; i < input.length() - 1; ++i) {
            if (input.charAt(i) == input.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private String encode(String input) {
        String replaced = input;
        for (int i = 0; i < 4; ++i) {
            replaced = replaced.replaceAll(words[i], String.valueOf(i));
        }
        return replaced;
    }
}