public class Solution {

    private final JadenCaseConverter jadenCaseConverter = new JadenCaseConverter();

    public String solution(String letter) {
        return jadenCaseConverter.convert(letter);
    }
}

class JadenCaseConverter {
    private static char SPACE = ' ';

    private boolean upper = true;

    public String convert(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); ++i) {
            if (upper && input.charAt(i) != SPACE) {
                addAlphabetToUpperCase(input, stringBuilder, i);
                continue;
            }
            if (input.charAt(i) == SPACE) {
                upper = true;
            }
            stringBuilder.append(Character.toLowerCase(input.charAt(i)));
        }
        return stringBuilder.toString();
    }

    private void addAlphabetToUpperCase(String input, StringBuilder stringBuilder, int i) {
        char ch = Character.toUpperCase(input.charAt(i));
        stringBuilder.append(ch);
        upper = false;
    }
}