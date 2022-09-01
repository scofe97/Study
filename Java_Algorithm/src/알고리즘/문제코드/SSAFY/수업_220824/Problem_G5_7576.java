package 알고리즘.문제코드.SSAFY.수업_220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G5_7576 {

    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static int[][] graph;
    static List<Point> start;

    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        start = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                graph[i][j] = temp;
                if(temp == 1) start.add(new Point(i,j));

            }
        }
        int result = bfs();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(graph[i][j] == 0){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(result);

    }

    static int bfs(){
        Queue<Point> queue = new LinkedList<>();
        for (Point point : start) {
            queue.offer(point);
        }

        int result = 0;

        while (!queue.isEmpty()){
            Point temp = queue.poll();

            result = Math.max(result,  temp.step);

            for (int i = 0; i < 4; i++) {
                int mx = temp.x + dir_x[i];
                int my = temp.y + dir_y[i];

                if (mx >= n || mx < 0 || my >= m || my < 0) continue;

                if (graph[mx][my] == 0) {
                    graph[mx][my] = 1;
                    queue.offer(new Point(mx, my, temp.step + 1));
                }
            }
        }

        return result;
    }

    static class Point{
        int x;
        int y;
        int step;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }

}
