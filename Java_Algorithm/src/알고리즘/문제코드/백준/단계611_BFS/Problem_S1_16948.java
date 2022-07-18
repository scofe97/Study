package 알고리즘.문제코드.백준.단계611_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_S1_16948 {

    static int[] dir_x = {-2, -2, 0, 0, 2, 2};
    static int[] dir_y = {-1, 1, -2, 2, -1, 1};
    static int size;
    static boolean[][] visited;

    static Point start;
    static Point end;

    static int result;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        visited = new boolean[size][size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        result = -1;

        bfs();
        System.out.println(result);

    }

    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (temp.x == end.x && temp.y == end.y) {
                result = temp.step;
                return;
            }
            temp.step++;

            for (int i = 0; i < 6; i++) {
                int temp_x = temp.x + dir_x[i];
                int temp_y = temp.y + dir_y[i];
                if (temp_x >= 0 && temp_x < size && temp_y >= 0 && temp_y < size) {
                    if (!visited[temp_x][temp_y]) {
                        visited[temp_x][temp_y] = true;
                        queue.add(new Point(temp_x, temp_y, temp.step));
                    }
                }
            }

        }
    }

    static class Point {
        int x;
        int y;
        int step;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
            step = 0;
        }

        Point(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}
