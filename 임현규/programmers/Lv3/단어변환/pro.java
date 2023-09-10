import java.util.*;

class Solution {
    
    public int solution(String begin, String target, String[] words) {
        return bfs(begin, target, words);
    }
    
    private int bfs(String start, String target, String[] words) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];
        q.add(new Node(start, 0));
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.word.equals(target)) {
                return node.count;
            }
            for (int i = 0; i < words.length; ++i) {
                if (!visited[i] && canChange(node.word, words[i])) {
                    q.add(new Node(words[i], node.count + 1));
                    visited[i] = true;
                }
            }
        }
        return 0;
    }
    
    private boolean canChange(String word, String otherWord) {
        int diffCount = 0;
        for (int i = 0; i < word.length(); ++i) {
            if (word.charAt(i) != otherWord.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount == 1;
    }
    
    private static class Node {
        private final String word;
        private final int count;
        
        public Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
        
        @Override
        public String toString() {
            return String.format("Node(%s, %d)", word, count);
        }
    }
}