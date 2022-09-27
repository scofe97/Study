package 알고리즘.문제코드.백준.싸피_스터디.연습8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_G3_1947 {

    // i번째 선물이 j에게 갔고 j의 선물이 i에게 온 경우  (n-2)
    // i번째 선물이 j에게 갔지만 j의 선물은 i에게 오지 않음 (n-1)
    //
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[1000001];

        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i < 1000001; i++) {
            dp[i] = ((i-1)*(dp[i-1] + dp[i-2])) % 1000000000;
        }

        System.out.println(dp[n]);
    }
}
