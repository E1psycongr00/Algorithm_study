class Solution {
	public int[] answerQueries(int[] nums, int[] queries) {
		Arrays.sort(nums);
		int[] pSums = new int[nums.length + 1];
		for (int i = 0; i < nums.length; i++) {
			pSums[i+1] = pSums[i] + nums[i];
		}
		int[] result = new int[queries.length];
		for (int k = 0; k < queries.length; k++) {
			for (int i = 0; i < pSums.length; i++) {
				int startSum = pSums[i];
				int target = queries[k];
				int maxLength = maxParameterSearch(pSums, queries[k], i, pSums.length - 1);
				result[k] = Integer.max(result[k], maxLength);
			}
		}
		return result;
	}

	private int maxParameterSearch(int[] pSums, int target, int low, int high) {
		int answer = 0;
        int left = low;
        int right = high;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (pSums[mid] - pSums[low] <= target) {
				answer = Integer.max(answer, mid - low);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return answer;
	}
}