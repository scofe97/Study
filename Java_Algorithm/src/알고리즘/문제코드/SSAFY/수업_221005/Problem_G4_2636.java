package 알고리즘.문제코드.SSAFY.수업_221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_G4_2636 {

    static int n, m;
    static int[][] map;

    static int[] dir_x = {-1, 1, 0, 0};
    static int[] dir_y = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int zero_cnt;
        int cheese_cnt;
        int delete_cnt;
        int time = 0;
        Stack<Integer> stack = new Stack<>();

        while(true){
            zero_cnt = 0;
            cheese_cnt = 0;
            delete_cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(map[i][j] == 0) zero_cnt++;
                    else if(map[i][j] == 1) cheese_cnt++;
                    else if(map[i][j] == -1) {
                        delete_cnt++;
                        map[i][j] = 0;
                    }
                }
            }

            if(zero_cnt + delete_cnt == n*m){
                System.out.println(time);
                System.out.println(stack.pop());
                break;
            }else{
                stack.push(cheese_cnt);
            }
            bfs();
            time++;
        }


    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0));
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int mx = temp.x + dir_x[i];
                int my = temp.y + dir_y[i];

                if (mx < 0 || mx >= n || my < 0 || my >= m) continue;

                if (!visited[mx][my]) {
                    if (map[mx][my] == 0) {
                        visited[mx][my] = true;
                        queue.offer(new Point(mx, my));
                    }

                    else if (map[mx][my] == 1) {
                        visited[mx][my] = true;
                        map[mx][my] = -1;
                    }
                }
            }

        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
