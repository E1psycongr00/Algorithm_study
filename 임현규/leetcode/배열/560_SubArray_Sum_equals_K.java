class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] pSums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            pSums[i + 1] = pSums[i] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (pSums[j+1] - pSums[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }
}