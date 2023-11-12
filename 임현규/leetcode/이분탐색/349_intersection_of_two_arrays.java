class Solution {
	public int[] intersection(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Set<Integer> result = new HashSet<>();
        for (int n2 : nums2) {
            if (binarySearch(nums1, n2)) {
                result.add(n2);
            }
        }
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
	}

    private boolean binarySearch(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid - 1;
            }
        }
        return false;
    }
}