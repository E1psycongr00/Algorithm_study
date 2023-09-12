class Solution {
    public int solution(int sticker[]) {
        if (sticker.length == 1) {
            return sticker[0];
        }
        if (sticker.length == 2) {
            return Integer.max(sticker[0], sticker[1]);
        }
        int[] dp1 = new int[sticker.length];
        dp1[0] = sticker[0];
        dp1[1] = Integer.max(sticker[0], sticker[1]);
        for (int i = 2; i < sticker.length - 1; i++) {
            dp1[i] = Integer.max(dp1[i-1], dp1[i-2] + sticker[i]);
        }
        int[] dp2 = new int[sticker.length];
        dp2[1] = sticker[1];
        dp2[2] = Integer.max(sticker[1], sticker[2]);
        for (int i = 3; i < sticker.length; i++) {
            dp2[i] = Integer.max(dp2[i-1], dp2[i-2] + sticker[i]);
        }
        return Integer.max(dp1[sticker.length - 2], dp2[sticker.length-1]);
        
    }
}