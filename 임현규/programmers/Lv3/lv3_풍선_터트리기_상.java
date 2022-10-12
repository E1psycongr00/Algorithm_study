/**
 * 살아남을 하나의 요소를 기준으로 왼쪽 오른쪽 범위의 최소값과 비교한다.
 * 이 때 최소값을 매번 O(N)만큼 비교하기는 힘드므로 DP를 활용해서 미리 좌측 최소값과 우측 최소값을 저장해놓는다.
 * 현 요소를 기준으로 left, right 중 하나 이상 더 작은 값을 가진다면 갯수를 세주고 양측 최소값이 모두 큰 경우는
 * 세지 않는다.(조건에 무조껀 인접값중 큰값을 지우지만 작은값도 한번은 지울 수 있음)
 * 
 */
class Solution {

    public int solution(int[] a) {
        int n = a.length;
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        leftMin[0] = a[0];
        rightMin[n - 1] = a[n - 1];
        for (int i = 1; i < n; ++i) {
            leftMin[i] = Math.min(leftMin[i - 1], a[i]);
        }
        for (int i = n - 1 - 1; i > 0; --i) {
            rightMin[i] = Math.min(rightMin[i + 1], a[i]);
        }
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (a[i] <= leftMin[i] || a[i] <= rightMin[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}
