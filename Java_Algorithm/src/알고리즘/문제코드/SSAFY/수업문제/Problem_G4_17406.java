package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_G4_17406 {
    static int n;
    static int m;
    static int cnt;

    static boolean[] visited;
    static int[][] list;
    static int[] dir_x = {0, 1, 0, -1};
    static int[] dir_y = {1, 0, -1, 0};
    static List<int[]> point;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cnt = Integer.parseInt(st.nextToken());
        point = new ArrayList<>();
        visited = new boolean[cnt];

        list = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                list[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int size = Integer.parseInt(st.nextToken());
            point.add(new int[]{x, y, size});
        }

        dfs(list, 0);
        System.out.println(min);

    }

    public static void dfs(int[][] copy, int step) {
        if (step == cnt) {
            for (int[] s1 : copy) {
                int sum = 0;
                for (int s2 : s1) {
                    sum += s2;
                }
                min = Math.min(min, sum);
            }
            return;
        }

        for (int i = 0; i < cnt; i++) {
            if (!visited[i]) {
                visited[i] = true;

                int[][] copy2 = new int[n][m];
                for (int j = 0; j < copy.length; j++) { // 반복문 + ArrayCopy
                    System.arraycopy(copy[j], 0, copy2[j], 0, copy2[j].length);
                }

                int[] temp = point.get(i);
                for (int j = 1; j <= temp[2]; j++) {
                    rotate(copy2, temp[0] - j, temp[1] - j, copy2[temp[0] - j][temp[1] - j], 0, 0, j);
                }

                dfs(copy2, step + 1);
                visited[i] = false;
            }
        }
    }

    public static void rotate(int[][] copy, int x, int y, int temp, int dir, int step, int size) {
        if (step == size * 2) {
            step = 0;
            dir++;
            if (dir == 4) {
                return;
            }
        }
        int m_x = x + dir_x[dir];
        int m_y = y + dir_y[dir];
        int temp2 = copy[m_x][m_y];
        copy[m_x][m_y] = temp;

        rotate(copy, m_x, m_y, temp2, dir, step + 1, size);

    }
}
