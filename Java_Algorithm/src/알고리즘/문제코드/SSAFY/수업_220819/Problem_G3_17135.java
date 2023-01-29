package 알고리즘.문제코드.SSAFY.수업_220819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17135
public class Problem_G3_17135 {
    static StringBuilder sb;
    static StringTokenizer st;

    static int n, m, range, result;
    static int[][] list;
    static boolean[] visited;

    static int[] dir_x = {0, -1, 0};
    static int[] dir_y = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()) + 1;
        m = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());
        list = new int[n][m];
        visited = new boolean[m];

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                list[i][j] = temp;
            }
        }

        dfs(0);
        System.out.println(result);
    }

    static int[][] listCopy(int[][] list) {
        int[][] temp = new int[n][m];

        for (int i = 0; i < list.length; i++) {
            System.arraycopy(list[i], 0, temp[i], 0, list[i].length);
        }
        return temp;
    }

    static void dfs(int step) {
        if (step == 3) {
            Set<Point> temp;
            int cnt = 0;
            int[][] listCopy = listCopy(list);

            // 내려가는 과정
            for (int i = 0; i < n - 1; i++) {

                // 중복되면 1번만 체크
                temp = new HashSet<>();
                for (int j = 0; j < m; j++) {
                    if (listCopy[n - 1][j] == 1) {
                        Point p = bfs(listCopy, n - 2, j);
                        if (p != null) temp.add(p);
                    }
                }

                cnt += temp.size();
                for (Point point : temp) {
                    listCopy[point.x][point.y] = 0;
                }

                for (int j = n - 2; j >= 0; j--) {
                    if (j != 0) listCopy[j] = listCopy[j - 1];
                    else listCopy[j] = new int[m];
                }
            }

            result = Math.max(result, cnt);
            return;
        }

        for (int i = step; i < m; i++) {
            if (list[n - 1][i] != 1) {
                list[n - 1][i] = 1;
                dfs(step + 1);
                list[n - 1][i] = 0;
            }
        }
    }

    static Point bfs(int[][] list, int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        PriorityQueue<Point> pq = new PriorityQueue<>(((o1, o2) -> o1.y - o2.y));
        queue.add(new Point(x, y, 1));

        boolean[][] visited = new boolean[n][m];
        visited[x][y] = true;
        if (list[x][y] == 1) pq.offer(new Point(x, y, 1));

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (temp.range == range || (!pq.isEmpty() && temp.range > pq.peek().range)) break;

            for (int i = 0; i < 3; i++) {
                int mx = temp.x + dir_x[i];
                int my = temp.y + dir_y[i];

                if (mx < 0 || mx >= n - 1 || my < 0 || my >= m || visited[mx][my]) continue;
                visited[mx][my] = true;
                queue.offer(new Point(mx, my, temp.range + 1));
                if (list[mx][my] == 1 && (pq.isEmpty() ||(!pq.isEmpty() && temp.range + 1 <= pq.peek().range)))
                    pq.offer(new Point(mx, my, temp.range + 1));
            }
        }

        return pq.poll();
    }

    static class Point {
        int x;
        int y;
        int range;

        public Point(int x, int y, int range) {
            this.x = x;
            this.y = y;
            this.range = range;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
