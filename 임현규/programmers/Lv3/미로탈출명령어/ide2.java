class Solution {

	private static final String IMPOSSIBLE = "impossible";

	public String solution(int n, int m, int y, int x, int r, int c, int k) {
		int distance = calDistance(y, x, r, c);
		if (cannotArrive(distance, k)) {
			return IMPOSSIBLE;
		}

		StringBuilder sb = new StringBuilder();
		while (y < n && k > calDistance(y, x, r, c)) {
			k--;
			y++;
			sb.append("d");
		}
		while (x > 1 && k > calDistance(y, x, r, c)) {
			k--;
			x--;
			sb.append("l");
		}
		while (k > calDistance(y, x, r, c)) {
			k -= 2;
			sb.append("rl");
		}
		if (r - y > 0) {
			sb.append("d".repeat(r - y));
		} else if (c - x < 0) {
			sb.append("l".repeat(x - c));
		} else if (c - x > 0) {
			sb.append("r".repeat(c - x));
		} else if (r - y < 0) {
			sb.append("u".repeat(y - r));
		}

		return sb.toString();
	}

	private int calDistance(int y, int x, int r, int c) {
		return Math.abs(y - r) + Math.abs(x - c);
	}

	private boolean cannotArrive(int distance, int k) {
		return k < distance || (k - distance) % 2 == 1;
	}
}