class Solution {

    private static final int BORDER_NUMBER = 5;

    public int solution(int storey) {
        int count = 0;
        while (storey > 0) {
            int units = getUnits(storey);
            if (units > BORDER_NUMBER) {
                int rest = 10 - units;
                count += rest;
                storey += rest;
            }
            if (units < BORDER_NUMBER) {
                count += units;
                storey -= units;
            }
            if (units == BORDER_NUMBER) {
                int tenth = (storey % 100) / 10;
                if (tenth < BORDER_NUMBER) {
                    count += units;
                    storey -= units;
                } else {
                    count += units;
                    storey += units;
                }
            }
            storey /= 10;
        }
        return count;
    }

    private int getUnits(int number) {
        return number % 10;
    }
}