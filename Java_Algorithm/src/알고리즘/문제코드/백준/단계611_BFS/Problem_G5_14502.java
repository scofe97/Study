package 알고리즘.문제코드.백준.단계611_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G5_14502 {


    static int n;
    static int m;
    static ArrayList<Point> virus;
    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        virus = new ArrayList<>();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] graph = new int[n][m];
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }

        for (int i = 0; i < n * m; i++) {
            if (graph[i / m][i % m] != 0) {
                continue;
            }
            for (int j = i + 1; j < n * m; j++) {
                if (graph[j / m][j % m] != 0) {
                    continue;
                }
                for (int k = j + 1; k < n * m; k++) {
                    if (k >= n * m || graph[k / m][k % m] != 0) {
                        continue;
                    }

                    graph[i / m][i % m] = 1;
                    graph[j / m][j % m] = 1;
                    graph[k / m][k % m] = 1;

                    result = Math.max(result, bfs(graph));

                    graph[i / m][i % m] = 0;
                    graph[j / m][j % m] = 0;
                    graph[k / m][k % m] = 0;
                }
            }
        }

        System.out.println(result);
    }

    static int bfs(int[][] graph) {
        Queue<Point> queue = new LinkedList<>();
        for (Point point : virus) {
            queue.add(point);
        }

        int[][] temp_graph = new int[n][m];
        for (int m = 0; m < graph.length; m++) {
            System.arraycopy(graph[m], 0, temp_graph[m], 0, temp_graph[m].length);
        }

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            for (int i = 0; i < 4; i++) {
                if (temp.x + dir_x[i] < n && temp.x + dir_x[i] >= 0
                        && temp.y + dir_y[i] < m && temp.y + dir_y[i] >= 0) {
                    if (temp_graph[temp.x + dir_x[i]][temp.y + dir_y[i]] == 0) {
                        temp_graph[temp.x + dir_x[i]][temp.y + dir_y[i]] = 2;
                        queue.add(new Point(temp.x + dir_x[i], temp.y + dir_y[i]));
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp_graph[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


}
