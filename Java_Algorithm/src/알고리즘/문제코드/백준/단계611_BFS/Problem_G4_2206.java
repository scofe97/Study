package 알고리즘.문제코드.백준.단계611_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G4_2206 {

	static int[][] graph;
	static int n;
	static int m;
	static int[] dir_x = { 1, 0, -1, 0 };
	static int[] dir_y = { 0, 1, 0, -1 };
	static int result = -1;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		graph = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 0; j < m; j++) {
				graph[i][j] = Integer.parseInt(temp[j]);
			}
		}

		bfs();
		System.out.println(result);
	}

	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, true, 1));
		boolean[][][] visited = new boolean[2][n][m];
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {
			Point temp = queue.poll();

			if (temp.x == n - 1 && temp.y == m - 1) {
				result = temp.step;
				return;
			}

			for (int i = 0; i < 4; i++) {
				int temp_move_x = temp.x + dir_x[i];
				int temp_move_y = temp.y + dir_y[i];
				if (temp_move_x < n && temp_move_x >= 0 && temp_move_y < m && temp_move_y >= 0) {

					if(graph[temp_move_x][temp_move_y] == 1) {
						if(temp.power == true && !visited[1][temp_move_x][temp_move_y]) {
							visited[1][temp_move_x][temp_move_y] = true;
							queue.offer(new Point(temp_move_x,temp_move_y, false, temp.step + 1));
						}
					}
					
					else if(graph[temp_move_x][temp_move_y] == 0) {
						if(temp.power == true && !visited[0][temp_move_x][temp_move_y]) {
							visited[0][temp_move_x][temp_move_y] = true;
							queue.offer(new Point(temp_move_x,temp_move_y, true, temp.step + 1));
						}else if(temp.power == false && !visited[1][temp_move_x][temp_move_y]) {
							visited[1][temp_move_x][temp_move_y] = true;
							queue.offer(new Point(temp_move_x,temp_move_y, false, temp.step + 1));
						}
					}
				}
			}

		}
	}

	static class Point {
		int x;
		int y;
		boolean power;
		int step;

		Point(int x, int y, boolean power, int step) {
			this.x = x;
			this.y = y;
			this.power = power;
			this.step = step;
		}
	}

}

/*
5 5
00100
11000
00110
01011
00000

6 5
01000
00110
10110
00111
01111
01000
*/

