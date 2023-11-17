import java.util.*;

class Solution {
	public int solution(int[][] scores) {
		int rank = 0;

		int wanHoAttitude = scores[0][0];
		int wanHoPeer = scores[0][1];

		Arrays.sort(scores, (o1, o2) -> {
			if (o1[0] == o2[0]) {
				return o1[1] - o2[1];
			}
			return o2[0] - o1[0];
		});

		int threshold = 0;
		for (int[] score : scores) {
			if (wanHoAttitude < score[0] && wanHoPeer < score[1]) {
				return -1;
			}
			if (threshold <= score[1]) {
				if (wanHoAttitude + wanHoPeer < score[0] + score[1]) {
					rank++;
				}
				threshold = score[1];
			}
		}
		return rank;
	}
}