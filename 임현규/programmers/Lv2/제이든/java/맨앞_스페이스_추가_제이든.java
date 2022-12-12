public class Solution {

    public String solution(String letter) {
        String newLetter = " " + letter.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < newLetter.length(); ++i) {
            if (newLetter.charAt(i - 1) == ' ' && Character.isAlphabetic(newLetter.charAt(i))) {
                stringBuilder.append(Character.toUpperCase(newLetter.charAt(i)));
                continue;
            }
            stringBuilder.append(newLetter.charAt(i));
        }
        return stringBuilder.toString();
    }
}