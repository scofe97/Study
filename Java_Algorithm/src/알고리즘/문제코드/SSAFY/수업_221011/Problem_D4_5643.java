package 알고리즘.문제코드.SSAFY.수업_221011;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXQsLWKd5cDFAUo
public class Problem_D4_5643 {

    static int n, m, ans, tcnt, scnt;
    static int[][] map;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine()); // 학생 수
            m = Integer.parseInt(br.readLine()); // 비교 횟수

            map = new int[n + 1][n + 1];
            ans = 0;

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                map[from][to] = 1; // from 보다 to 가 크다
            }

            for(int i=1; i<n+1; i++) {
                tcnt = 0;
                scnt = 0;
                taller(i, new boolean[n+1]);
                shorter(i, new boolean[n+1]);
                if(tcnt + scnt == n-1)
                    ans++;
            }

            System.out.println("#"+tc+" "+ans);

        }
    }

    static void taller(int from, boolean[] visited) {
        visited[from] = true;
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i] && map[from][i] == 1) {
                taller(i, visited);
                tcnt++;
            }
        }
    }

    static void shorter(int to, boolean[] visited) {
        visited[to] = true;
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i] && map[i][to] == 1) {
                shorter(i, visited);
                scnt++;
            }
        }
    }
}
