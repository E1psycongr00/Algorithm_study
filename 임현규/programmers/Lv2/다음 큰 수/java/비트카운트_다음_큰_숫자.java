class Solution {

    public int solution(int number) {
        int n = number + 1;
        int numberBitCount = Integer.bitCount(number);
        while (n <= 1_000_000) {
            int count = Integer.bitCount(n);
            if (numberBitCount == count) {
                break;
            }
            n++;
        }
        return n;
    }
}
