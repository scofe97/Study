package 알고리즘.문제코드.SSAFY.수업_220823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Problem_D4_7465 {
    static int[] set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            set = new int[n+1];
            for (int i = 1; i <= n; i++) {
                set[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                set.add(find(i));
            }

            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(set.size())
                    .append("\n");
        }
        System.out.println(sb);
    }

    static int find(int x){
        if(set[x] == x) return x;
        else return set[x] = find(set[x]);
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot < bRoot) set[bRoot] = aRoot;
        else set[aRoot] = bRoot;
    }
}
