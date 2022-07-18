package 알고리즘.문제코드.사전학습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Problem_D4_1226 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int SIZE = 16;
    static int[][] graph = new int[SIZE][SIZE];
    static int[] start = new int[2];
    static int[] end = new int[2];

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            tc = Integer.parseInt(br.readLine());

            // note : 그래프 초기화
            for (int i = 0; i < SIZE; i++) {
                String[] line = br.readLine().split("");
                for (int j = 0; j < SIZE; j++) {
                    int temp = Integer.parseInt(line[j]);

                    if(temp == 2){
                        start[0] = i;
                        start[1] = j;
                    } else if (temp == 3) {
                        end[0] = i;
                        end[1] = j;
                    }
                    graph[i][j] = temp;
                }
            }

            bfs();
            System.out.println("#" + tc + " " + result);
            result = 0;
        }
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[SIZE][SIZE];
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] temp_point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int temp_x = dx[i] + temp_point[0];
                int temp_y = dy[i] + temp_point[1];

                if(temp_x >= 0 && temp_x < SIZE && temp_y >= 0 && temp_y < SIZE && !visited[temp_x][temp_y] && graph[temp_x][temp_y] != 1){
                    visited[temp_x][temp_y] = true;

                    if(temp_x == end[0] && temp_y == end[1]){
                        result = 1;
                        return;
                    }
                    int[] temp_point2 = {temp_x, temp_y};
                    queue.offer(temp_point2);
                }
            }
        }
    }
}
