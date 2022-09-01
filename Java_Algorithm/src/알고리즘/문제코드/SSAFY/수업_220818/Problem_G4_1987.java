package 알고리즘.문제코드.SSAFY.수업_220818;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1987
public class Problem_G4_1987 {

    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};

    static int n;
    static int m;

    static char[][] graph;
    static boolean[] visited_alpha;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = 0;

        graph = new char[n][m];
        visited_alpha = new boolean[26];
        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine()
                    .toCharArray();
        }

        visited_alpha[graph[0][0] - 'A'] = true;
        dfs(0, 0, 1);
        System.out.println(result);

    }

    static void dfs(int x, int y, int step) {

        result = Math.max(result, step);

        for (int i = 0; i < 4; i++) {
            int mx = x + dir_x[i];
            int my = y + dir_y[i];

            if (mx < 0 || mx >= n || my < 0 || my >= m) continue;

            if (!visited_alpha[graph[mx][my] - 'A']) {
                visited_alpha[graph[mx][my] - 'A'] = true;
                dfs(mx, my, step + 1);
                visited_alpha[graph[mx][my] - 'A'] = false;
            }
        }
    }
}
