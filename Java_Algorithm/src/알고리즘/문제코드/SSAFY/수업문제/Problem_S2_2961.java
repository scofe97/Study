package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S2_2961 {

    static int n;
    static int[][] list;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new int[n][2];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int taste1 = Integer.parseInt(st.nextToken());
            int taste2 = Integer.parseInt(st.nextToken());

            list[i][0] = taste1;
            list[i][1] = taste2;
        }

        dfs(0, 1, 0, 0);

        System.out.println(result);
    }

    public static void dfs(int step, int t1, int t2, int cnt){
        if(step == n){
            if(cnt != 0){
                result = Math.min(result, Math.abs(t1 - t2));
            }
            return;
        }

        dfs(step+1, t1 * list[step][0], t2 + list[step][1], cnt + 1);
        dfs(step+1, t1, t2, cnt);

    }
}
