package 알고리즘.문제코드.SSAFY.수업_221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G1_1194 {

    static int n, m, start_n, start_m;
    static char[][] map;

    static int[] dir_x = {0, -1, 0, 1};
    static int[] dir_y = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            char[] temp = br.readLine()
                    .toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = temp[j];
                if (temp[j] == '0') {
                    start_n = i;
                    start_m = j;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start_n, start_m, 0, 1));
        boolean[][][] visited = new boolean[n][m][128];


        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (map[temp.x][temp.y] == '1') return temp.cnt;

            for (int i = 0; i < 4; i++) {
                int mx = temp.x + dir_x[i];
                int my = temp.y + dir_y[i];
                int key = temp.key;

                if (mx < 0 || mx >= n || my < 0 || my >= m) continue;
                if (map[mx][my] == '#') continue;
                if (Character.isUpperCase(map[mx][my]) && (temp.key & (1 << (map[mx][my] - 'A' + 1))) == 0) continue;

                if (Character.isLowerCase(map[mx][my])) {
                    if((key & (1 << (map[mx][my] - 'a' + 1))) == 0){
                        key |= (1 << (map[mx][my] - 'a' + 1));
                    }
                }

                if (!visited[mx][my][temp.key]) {
                    visited[mx][my][temp.key] = true;
                    queue.offer(new Point(mx, my, temp.cnt + 1,key));
                }
            }
        }

        return -1;
    }

    static class Point {
        int x;
        int y;
        int cnt;
        int key;

        public Point(int x, int y, int cnt, int key) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }
}
