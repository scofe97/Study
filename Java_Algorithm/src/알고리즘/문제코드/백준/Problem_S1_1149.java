package 알고리즘.문제코드.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_S1_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][3];
        int max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[n][3];
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(arr[i][0] +  dp[i-1][1], arr[i][0] +  dp[i-1][2]);
            dp[i][1] = Math.min(arr[i][1] +  dp[i-1][0], arr[i][1] +  dp[i-1][2]);
            dp[i][2] = Math.min(arr[i][2] +  dp[i-1][0], arr[i][2] +  dp[i-1][1]);
        }

        System.out.println(Arrays.stream(dp[n-1]).min().getAsInt());
    }
}
