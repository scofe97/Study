package 알고리즘.문제코드.백준.싸피_스터디.연습5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G3_2234 {

	static int n;
	static int m;
	static int num;

	static boolean[][] visited;
	static int[][] list;

	static int[] dir_x = { 0, 1, 0, -1 };
	static int[] dir_y = { 1, 0, -1, 0 };
	static int[] bit = { 2, 3, 0, 1 };

	static int result1;
	static int result2;
	static int result3;

	public static void main(String[] args) throws IOException {

		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		num = 0;

		list = new int[n][m];
		visited = new boolean[n][m];

		result1 = 0;
		result2 = 0;
		result3 = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				list[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j]) {
					result2 = Math.max(result2, bfs(i, j));
					result1++;
				}
			}
		}


		System.out.println(result1);
		System.out.println(result2);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for(int k = 0; k< 4; k++) {
					if((list[i][j] & 1 << bit[k]) != 0) {
						visited = new boolean[n][m];
						list[i][j] -= 1 << bit[k];
						result2 = Math.max(result2, bfs(i, j));
						list[i][j] += 1 << bit[k];
					}
				}
			}
		}
		
		System.out.println(result2);

	}

	static int bfs(int x, int y) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { x, y });
		visited[x][y] = true;
		int step = 1;

		while (!queue.isEmpty()) {
			int[] temp = queue.poll();

			for (int i = 0; i < 4; i++) {
				int mx = temp[0] + dir_x[i];
				int my = temp[1] + dir_y[i];
				int value = list[temp[0]][temp[1]];

				if (mx >= n || mx < 0 || my >= m || my < 0) {
					continue;
				}

				if ((value & 1 << bit[i]) == 0 && !visited[mx][my]) {
					visited[mx][my] = true;
					step++;
					queue.offer(new int[] { mx, my });
				}
			}
		}
		return step;

	}
}
