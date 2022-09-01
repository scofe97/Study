package 알고리즘.문제코드.SSAFY.수업_220818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_G2_3109 {

    static int n;
    static int m;
    static boolean[][] visited;

    static int[] dir_x = {-1, 0, 1};
    static int[] dir_y = {1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (temp[j] == 'x') {
                    visited[i][j] = true;
                }
            }
        }

        System.out.println(pipe());

    }

    private static int pipe(){
        int temp = 0;
        for (int i = 0; i < n; i++) {
            if(dfs(i, 0)) temp++;
        }

        return temp;
    }

    private static boolean dfs(int x, int y) {
        if(y == m-1) return true;

        for (int i = 0; i < 3; i++) {
            int mx = x + dir_x[i];
            int my = y + dir_y[i];

            if(mx < 0 || mx >= n || my < 0 || my >= m) continue;

            if(!visited[mx][my]){
                visited[mx][my] = true;

                if(dfs(mx, my)) return true;
            }
        }
        return false;
    }
}
