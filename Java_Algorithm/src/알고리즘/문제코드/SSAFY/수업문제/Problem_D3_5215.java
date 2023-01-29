package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_5215 {

    static int cnt;
    static int weight;
    static boolean[] visited;
    static int[][] list;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            cnt = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            list = new int[cnt][2];

            for (int i = 0; i < cnt; i++) {
                st = new StringTokenizer(br.readLine());
                int point = Integer.parseInt(st.nextToken());
                int cal = Integer.parseInt(st.nextToken());

                list[i][0] = point;
                list[i][1] = cal;
            }


        }
    }

    static void dfs(int step, int w, int p){
        if(step == cnt){
            return;
        }

        for (int i = 0; i < cnt; i++) {
            if(!visited[i]){
                visited[i] = true;

                int pp = p+list[i][0];
                int pw = w+list[i][1];

                if(pw <= weight){
                    dfs(step+1, pw, pp);
                }
            }
        }
    }
}
