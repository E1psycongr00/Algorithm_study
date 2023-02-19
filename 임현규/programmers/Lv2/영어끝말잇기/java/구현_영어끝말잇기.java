import java.util.HashSet;
import java.util.Set;

public class Solution {

    public int[] solution(int n, String[] words) {
        return LastWordConnectionGame.findLoser(n, words);
    }
}

class LastWordConnectionGame {

    public static int[] findLoser(int n, String[] words) {
        Set<String> usage = new HashSet<>();
        int[] turns = new int[n + 1];
        turns[1] = 1;
        usage.add(words[0]);
        for (int i = 1; i < words.length; ++i) {
            int player = (i % n) + 1;
            turns[player] += 1;
            if (!isBeforeNodesLastName(words, i)) {
                return new int[]{player, turns[player]};
            }
            if (usage.contains(words[i])) {
                return new int[]{player, turns[player]};
            }
            usage.add(words[i]);
        }
        return new int[]{0, 0};
    }

    private static boolean isBeforeNodesLastName(String[] words, int index) {
        return words[index].charAt(0) == words[index - 1].charAt(words[index - 1].length() - 1);
    }
}