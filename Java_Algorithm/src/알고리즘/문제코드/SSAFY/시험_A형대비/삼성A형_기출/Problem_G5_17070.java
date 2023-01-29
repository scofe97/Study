package 알고리즘.문제코드.SSAFY.시험_A형대비.삼성A형_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_G5_17070 {

    static int size;
    static int graph[][];
    static boolean visited[][];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        size = Integer.parseInt(br.readLine());
        graph = new int[size][size];
        visited = new boolean[size][size];
        result = 0;

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        graph[0][0] = 1;
        graph[0][1] = 1;
        visited[0][0] = true;
        visited[0][1] = true;

        dfs(0, 1, 1);
        System.out.println(result);

    }

    static void dfs(int x, int y, int type) {
        if (x == size - 1 && y == size - 1) {
            result++;
            return;
        }

        if ((type == 1 || type == 3)
                && y + 1 < size
                && !visited[x][y + 1]
                && graph[x][y + 1] == 0) {
            visited[x][y + 1] = true;
            dfs(x, y + 1, 1);
            visited[x][y + 1] = false;
        }

        if ((type == 2 || type == 3)
                && x + 1 < size
                && !visited[x + 1][y]
                && graph[x + 1][y] == 0) {
            visited[x + 1][y] = true;
            dfs(x + 1, y, 2);
            visited[x + 1][y] = false;
        }

        if (x + 1 < size
                && y + 1 < size
                && !visited[x][y + 1]
                && !visited[x + 1][y]
                && !visited[x + 1][y + 1]
                && graph[x][y + 1] == 0
                && graph[x + 1][y] == 0
                && graph[x + 1][y + 1] == 0) {
            visited[x][y + 1] = true;
            visited[x + 1][y] = true;
            visited[x + 1][y + 1] = true;
            dfs(x + 1, y + 1, 3);
            visited[x][y + 1] = false;
            visited[x + 1][y] = false;
            visited[x + 1][y + 1] = false;
        }
    }
}
