class Solution {

    public String solution(int[] food) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < food.length; ++i) {
            int size = food[i] / 2;
            stringBuilder.append(String.valueOf(i).repeat(size));
        }
        String forward = stringBuilder.toString();
        String backward = stringBuilder.reverse().toString();
        return forward + "0" + backward;
    }
}
