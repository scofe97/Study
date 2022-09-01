package 알고리즘.문제코드.SSAFY.수업_220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2839
public class Problem_S4_2839 {

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int W = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;
        dfs(W, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result );
    }

    private static void dfs(int weight, int cnt){
        if(weight == 0){
            result = Math.min(result, cnt);
            return;
        }

        if(weight < 0){
            return;
        }

        if(weight >= 5){
            dfs(weight % 5, cnt + (weight/5));
        }
        dfs(weight - 3, cnt + 1);
    }
}
