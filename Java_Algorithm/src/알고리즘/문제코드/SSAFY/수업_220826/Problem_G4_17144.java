package 알고리즘.문제코드.SSAFY.수업_220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_G4_17144 {

    static int n;
    static int m;
    static int time;

    static int[][] graph;
    static List<Point> air;
    static List<Point> dust;

    static int[] dir_x = {0, -1, 0, 1};
    static int[] dir_y = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        time = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        air = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                graph[i][j] = temp;
                if (temp < 0) air.add(new Point(i, j, -1));
            }
        }

        for (int i = 0; i < time; i++) {
            dust = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if (graph[j][k] > 0) dust.add(new Point(j, k, graph[j][k]));
                }
            }

            for (Point point : dust) {
                if (point.cnt < 5) continue;

                int move = point.cnt / 5;
                for (int j = 0; j < 4; j++) {
                    int mx = point.x + dir_x[j];
                    int my = point.y + dir_y[j];

                    if (mx < 0 || my < 0 || mx >= n || my >= m || graph[mx][my] == -1) continue;

                    graph[point.x][point.y] -= move;
                    graph[mx][my] += move;
                }
            }

            dfs(air.get(0).x, 0);
            dfs(air.get(1).x,  1);
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] > 0) result += graph[i][j];
            }
        }

        System.out.println(result);
    }

    static void dfs(int x,  int type) {
        if (type == 0) {
            // 위쪽 공기청정기의 바람은 반시계방향 순환,
            // 아래로 당기기
            for (int i = x - 1; i > 0; i--)
                graph[i][0] = graph[i - 1][0];
            // 왼쪽으로 당기기
            for (int i = 0; i < m - 1; i++)
                graph[0][i] = graph[0][i + 1];
            // 위로 당기기
            for (int i = 0; i < x; i++)
                graph[i][m - 1] = graph[i + 1][m - 1];
            // 오른쪽으로 당기기
            for (int i = m - 1; i > 1; i--)
                graph[x][i] = graph[x][i - 1];
            // 공기청정기에서 부는 바람은 미세먼지가 없는 바람

        } else {
            // 아래쪽 공기청정기의 바람은 시계방향으로 순환
            // 위로 당기기
            for (int i = x + 1; i < n - 1; i++)
                graph[i][0] = graph[i + 1][0];
            // 왼쪽으로 당기기
            for (int i = 0; i < m - 1; i++)
                graph[n - 1][i] = graph[n - 1][i + 1];
            // 아래로 당기기
            for (int i = n - 1; i > x; i--)
                graph[i][m - 1] = graph[i - 1][m - 1];
            // 오른쪽으로 당기기
            for (int i = m - 1; i > 1; i--)
                graph[x][i] = graph[x][i - 1];
            // 공기청정기에서 부는 바람은 미세먼지가 없는 바람
        }
        graph[x][1] = 0;


    }

    static class Point {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
