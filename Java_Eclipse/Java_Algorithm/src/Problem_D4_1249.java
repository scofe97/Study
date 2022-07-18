
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Problem_D4_1249 {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static int[][] graph;
    public static int[][] ans;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int size = Integer.parseInt(br.readLine());
            graph = new int[size][size];
            ans = new int[size][size];
            min = Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                String[] temp = br.readLine().split("");
                for (int j = 0; j < size; j++) {
                    graph[i][j] = Integer.parseInt(temp[j]);
                }
            }

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    ans[i][j] = Integer.MAX_VALUE;
                }
            }
            ans[0][0] = 0;

            bfs(size);
            System.out.println("#"+tc+" "+min);
        }
    }

    public static void bfs(int size) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int y = poll[0];
            int x = poll[1];

            if (y == size - 1 && x == size - 1) {
                min = Math.min(min, ans[size-1][size-1]);
            }

            if (min <= ans[y][x]) {
                min = Math.min(min, ans[size-1][size-1]);
            }

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (nx < 0 || ny < 0 || nx >= graph.length || ny >= graph[0].length) {
                    continue;
                }

                if (ans[ny][nx] > ans[y][x] + graph[ny][nx]) {
                    ans[ny][nx] = ans[y][x] + graph[ny][nx];
                    queue.offer(new int[]{ny, nx});
                }
            }
        }
    }
}