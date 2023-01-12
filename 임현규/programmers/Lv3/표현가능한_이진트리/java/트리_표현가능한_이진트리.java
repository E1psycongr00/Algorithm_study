import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] solution(long[] numbers) {
        List<Integer> result = new ArrayList<>();
        for (long number : numbers) {
            String binary = toBinary(number);
            boolean isPossibleBinaryTree = isPossibleBinaryTree(binary);
            result.add(isPossibleBinaryTree ? 1 : 0);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private String toBinary(long number) {
        String binary = Long.toBinaryString(number);
        double log = Math.log(binary.length() + 1) / Math.log(2);
        int n = (int)log;
        if (log > n) {
            return "0".repeat((int)Math.pow(2, n + 1) - binary.length()-1) + binary;
        }
        return binary;
    }

    private boolean isPossibleBinaryTree(String number) {
        if (number.length() == 1) {
            return true;
        }
        int begin = 0;
        int end = number.length() - 1;
        int mid = (begin + end) / 2;
        int left = (begin + mid - 1) / 2;
        int right = (mid +1 + end) / 2;
        if (number.charAt(mid) == '0' && number.charAt(left) == '1') {
            return false;
        }
        if (number.charAt(mid) == '0' && number.charAt(right) == '1') {
            return false;
        }
        boolean l = isPossibleBinaryTree(number.substring(begin, mid));
        boolean r = isPossibleBinaryTree(number.substring(mid+1, end + 1));
        return l && r;
    }
}
