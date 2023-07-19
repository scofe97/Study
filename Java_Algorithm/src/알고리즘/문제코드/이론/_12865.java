package 알고리즘.문제코드.이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _12865 {

    static int N;
    static int K;
    static int[] W;
    static int[] V;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String[] s = br.readLine()
                .split(" ");

        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        W = new int[N + 1];
        V = new int[N + 1];
        dp = new int[K + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            s = br.readLine()
                    .split(" ");
            int w = Integer.parseInt(s[0]);
            int v = Integer.parseInt(s[1]);
            W[i] = w;
            V[i] = v;
        }


        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {

                if(W[j] > i) dp[i][j] = dp[i][j-1];
                else dp[i][j] = Math.max(dp[i][j-1], dp[i-W[j]][j-1] + V[j]);
            }
        }

        System.out.println(dp[K][N]);
    }
}
