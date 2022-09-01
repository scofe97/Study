package 알고리즘.문제코드.SSAFY.수업_220819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D4_3234 {
    static StringBuilder sb;
    static StringTokenizer st;

    static int n;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] weight_list = new int[n];
            boolean[] visited = new boolean[n];
            result = 0;
            
            int sum = 0;

            for (int i = 0; i < n; i++) {
                int temp = Integer.parseInt(st.nextToken());
                weight_list[i] = temp;
                sum += temp;
            }

            dfs(0, 0, 0, visited, weight_list, sum);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int step, int left, int right, boolean[] visited, int[] weight_list, int sum) {
        if(left >= right + sum){
            int cnt = 1;

            for (int i = 0; i < n - step; i++) {
                cnt *= 2;
            }

            for (int i = 1; i <= n - step; i++) {
                cnt *= i;
            }

            result += cnt;
            return;
        }
        
        if (step == n) {
            result++;
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(step + 1, left + weight_list[i], right, visited, weight_list, sum - weight_list[i]);
                if(left >= right + weight_list[i])dfs(step + 1, left, right + weight_list[i], visited, weight_list, sum - weight_list[i]);
                visited[i] = false;
            }
        }
    }
}
