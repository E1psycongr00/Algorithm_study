class Solution {
    public int largestAltitude(int[] gain) {
        int[] pSums = new int[gain.length + 1];
        for (int i = 0; i < gain.length; i++) {
            pSums[i + 1] = pSums[i] + gain[i];
        }
        return IntStream.of(pSums).max().orElseThrow();
    }
}