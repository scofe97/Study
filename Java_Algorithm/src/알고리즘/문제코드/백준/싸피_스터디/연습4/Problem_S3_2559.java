package 알고리즘.문제코드.백준.싸피_스터디.연습4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S3_2559 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] list = new int[size];
        int[] dp = new int[size + 1];

        for (int i = 0; i < size; i++) {
            int temp = Integer.parseInt(st.nextToken());
            list[i] = temp;
            dp[i + 1] = dp[i] + temp;

        }

        int max_tmp = Integer.MIN_VALUE;
        for (int i = day; i < size + 1; i++) {
            max_tmp = Math.max(max_tmp, dp[i] - dp[i - day]);
        }

        System.out.println(max_tmp);

    }
}
