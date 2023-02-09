import java.util.ArrayList;
import java.util.List;

class Solution {

    public String solution(String s, String skip, int index) {
        Alphabets alphabets = new Alphabets(skip);
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : s.toCharArray()) {
            char convert = alphabets.convert(ch, index);
            stringBuilder.append(convert);
        }
        return stringBuilder.toString();
    }
}

class Alphabets {

    private final List<Character> alphabets;

    public Alphabets(String skip) {
        alphabets = new ArrayList<>();
        for (char ch = 'a'; ch <= 'z'; ++ ch) {
            if (skip.contains(String.valueOf(ch))) {
                continue;
            }
            alphabets.add(ch);
        }
    }

    public char convert(char ch, int index) {
        int chIndex = (alphabets.indexOf(ch) + index) % alphabets.size();
        return alphabets.get(chIndex);
    }
}