package 알고리즘.문제코드.SSAFY.수업_220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G4_3055 {

    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static boolean[][] visited;

    static int m;
    static int n;

    static Point d;
    static Point s;
    static List<Point> water;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        water = new ArrayList<>();
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] temps = br.readLine()
                    .toCharArray();
            for (int j = 0; j < m; j++) {
                char temp = temps[j];

                if (temp == 'D') {
                    d = new Point(i, j);
                    graph[i][j] = 2502;
                } else if (temp == 'S') {
                    s = new Point(i, j, 1);
                    graph[i][j] = 0;
                } else if (temp == '*') {
                    water.add(new Point(i, j));
                    graph[i][j] = 0;
                } else if (temp == 'X') {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = 2501;
                }
            }
        }

        bfs_water();
        System.out.println(bfs());

    }

    static String bfs() {
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[n][m];
        queue.offer(s);
        visited[s.x][s.y] = true;

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (temp.x == d.x && temp.y == d.y) return Integer.toString(temp.step - 1);

            for (int i = 0; i < 4; i++) {
                int mx = temp.x + dir_x[i];
                int my = temp.y + dir_y[i];

                if (mx >= n || mx < 0 || my >= m || my < 0) continue;

                if (!visited[mx][my] && graph[mx][my] > temp.step) {
                    visited[mx][my] = true;
                    queue.offer(new Point(mx, my, temp.step + 1));
                }
            }
        }

        return "KAKTUS";
    }

    static void bfs_water() {
        Queue<Point> queue = new LinkedList<>();
        for (Point point : water) {
            queue.offer(point);
        }

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int mx = temp.x + dir_x[i];
                int my = temp.y + dir_y[i];

                if (mx >= n || mx < 0 || my >= m || my < 0) continue;

                if (graph[mx][my] == 2501) {
                    graph[mx][my] = temp.step + 1;
                    queue.offer(new Point(mx, my, temp.step + 1));
                }
            }
        }
    }

    static class Point {
        int x;
        int y;
        int step;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.step = 0;
        }

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }

    }

}
