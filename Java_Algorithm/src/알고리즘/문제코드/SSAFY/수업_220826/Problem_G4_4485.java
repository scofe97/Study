package 알고리즘.문제코드.SSAFY.수업_220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G4_4485 {

    static int[][] visited;
    static int size;
    static int[][] graph;
    static int result;

    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = 1;
        while ((size = Integer.parseInt(br.readLine())) != 0) {
            graph = new int[size][size];
            visited = new int[size][size];
            result = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                Arrays.fill(visited[i], Integer.MAX_VALUE);
                for (int j = 0; j < size; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            bfs();
            sb.append("Problem ")
                    .append(tc++)
                    .append(": ")
                    .append(result)
                    .append("\n");
        }
        System.out.println(sb);
    }

    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, graph[0][0]));
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if(temp.x == size-1 && temp.y == size-1){
                result = Math.min(result, temp.step);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int mx = temp.x + dir_x[i];
                int my = temp.y + dir_y[i];

                if(mx < 0 || my < 0 || mx >= size || my >= size) continue;

                if(visited[mx][my] > temp.step) {
                    visited[mx][my] = temp.step;
                    queue.offer(new Point(mx,my, temp.step + graph[mx][my]));
                }
            }
        }
    }

    static class Point {
        int x;
        int y;
        int step;

        public Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}
