import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

	private static final String IMPOSSIBLE = "impossible";
	private static final String[] directions = new String[] {"d", "l", "r", "u"};
	private static final int[] dy = new int[] {1, 0, 0, -1};
	private static final int[] dx = new int[] {0, -1, 1, 0};

	public String solution(int n, int m, int y, int x, int r, int c, int k) {
		return search(y-1, x-1, r-1, c-1, k, n, m);
	}
	
	private boolean isInArray(int cy, int cx, int n, int m) {
		return cy >= 0 && cy < n && cx >= 0 && cx < m;
	}
	
	private String search(int startR, int startC, int endR, int endC, int k, int n, int m) {
		Node startNode = new Node(startR, startC, "");
		PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(o -> o.path));
		pq.add(startNode);
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (node.y == endR && node.x == endC && node.path.length() == k) {
				return node.path;
			}
			for (int i = 0; i < directions.length; i++) {
				int ny = node.y + dy[i];
				int nx = node.x + dx[i];
				if (isInArray(ny, nx, n, m) && node.path.length() < k) {
					pq.add(new Node(ny, nx, node.path + directions[i]));
				}
			}
		}
		return IMPOSSIBLE;
	}
	
	private static class Node {
		private int y;
		private int x;
		private String path;
		
		public Node(int y, int x, String path) {
			this.y = y;
			this.x = x;
			this.path = path;
		}
        
        @Override
        public String toString() {
            return String.format("(%d, %d, %s)", y, x, path);
        }
	}
}