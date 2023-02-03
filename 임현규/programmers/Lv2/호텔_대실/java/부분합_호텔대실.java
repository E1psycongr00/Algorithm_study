class Solution {

    private static final int FULL_TIME_MINUTES = 24 * 60;
    private static final int CLEAN_TIME_MINUTES = 10;
    private static final String COLON = ":";

    public int solution(String[][] bookTimes) {
        int[] prefixSum = new int[FULL_TIME_MINUTES + CLEAN_TIME_MINUTES];
        for (String[] bookTime : bookTimes) {
            int startTime = calculateTime(bookTime[0]);
            int endTime = calculateTime(bookTime[1]);
            prefixSum[startTime] += 1;
            prefixSum[endTime + CLEAN_TIME_MINUTES] -= 1;
        }
        return calculateNeedRooms(prefixSum);
    }

    private int calculateNeedRooms(int[] prefixSum) {
        int room = 0;
        for (int i = 1; i < prefixSum.length; ++i) {
            prefixSum[i]+= prefixSum[i - 1];
            room = Integer.max(room, prefixSum[i]);
        }
        return room;
    }

    private int calculateTime(String time) {
        String[] split = time.split(COLON);
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }
}