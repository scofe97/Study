package 알고리즘.문제코드.백준.단계820_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S2_1654 {

    static int n;
    static int m;
    static long result;

    static int[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        list = new int[n];
        int start = 1;
        int end = 0;

        for (int i = 0; i < n; i++) {
            int temp = Integer.parseInt(br.readLine());
            list[i] = temp;
            end = Math.max(end, temp);
        }

        dfs(start, end);
        System.out.println(result);

    }

    static void dfs(long start, long end) {
        if(start > end) return;

        long point = (start + end) / 2;
        long div = 0;


        for (int i = 0; i < n; i++) {
            div += list[i] / point;
        }


        if (div < m) {
            dfs(start, point-1);
        } else {
            if(result < point) result = point;
            dfs(point+1, end);
        }
    }
}
