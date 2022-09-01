package 알고리즘.문제코드.SSAFY.수업_220818;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class AdjMatrixTest {

    static int[][] adjMatrix;
    static int N;
    static boolean visited[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int E = sc.nextInt();

        adjMatrix = new int[N][N]; // 0으로 자동 초기화
        visited = new boolean[N];

        for (int i = 0; i < E; i++) { // 간선 정보에 대한 부분만 덮어씀
            int from = sc.nextInt();
            int to = sc.nextInt();
            adjMatrix[to][from] = adjMatrix[from][to] = 1; // 무향 그래프
        }

        bfs();
        dfs(0);
    }

    private static void bfs() {
        // 0정점 시작
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        visited[0] = true;
        queue.offer(0);

        while (!queue.isEmpty()) {

            int cur = queue.poll();
            System.out.println((char) (cur + 'A'));

            for (int i = 0; i < N; i++) {
                if (!visited[i] && adjMatrix[cur][i] != 0) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
        System.out.println();
    }

    public static void dfs(int cur) {
        visited[cur] = true;
        System.out.println((char) (cur + 'A'));

        for (int i = 0; i < N; i++) {
            if (!visited[i] && adjMatrix[cur][i] != 0) {
                dfs(i);
            }
        }
    }
}

/*
7
8
0 1
0 2
1 3
1 4
3 5
4 5
5 6
2 6
*/
