package 알고리즘.문제코드.SSAFY.수업_220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S2_10971 {

    static int[][] graph;
    static int[] parent;
    static int size;
    static boolean[] visited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        size = Integer.parseInt(br.readLine());
        graph = new int[size][size];
        parent = new int[size];
        visited = new boolean[size];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < size; i++) {
            visited[i] = true;
            dfs(1, i, 0, i);
            visited[i] = false;
        }

        System.out.println(result);

    }

    static void dfs(int step, int cur, int pay, int start) {
        if (step == size) {
            if(graph[cur][start] != 0) result = Math.min(result,  pay + graph[cur][start]);
            return;
        }

        if(pay > result) return;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && graph[cur][i] != 0) {
                visited[i] = true;
                dfs(step + 1, i, pay + graph[cur][i], start);
                visited[i] = false;
            }
        }
    }
}
/*
4
0 4 0 3
3 0 4 0
0 3 0 4
4 0 3 0

 */
