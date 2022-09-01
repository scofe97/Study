package 알고리즘.문제코드.백준.단계801_분할정복_연습;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 링크 : https://www.acmicpc.net/problem/1074
public class Problem_S1_1074 {
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int size = (int) Math.pow(2.0, Double.parseDouble(st.nextToken()));
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dfs(0, 0, size, 0);
    }

    static void dfs(int x, int y, int step, int point) {
        if (step == 1 || (x == n && y == m)) {
            System.out.println(point);
            return;
        }

        int temp = step / 2;
        int p = temp * temp;

        if (n < x + temp && m < y + temp) {
            dfs(x, y, temp, point);
        } else if (n < x + temp) {
            dfs(x, y + temp, temp, point + p);
        } else if (m < y + temp) {
            dfs(x + temp, y, temp, point + p * 2);
        } else {
            dfs(x + temp, y + temp, temp, point + p * 3);
        }

    }
}

// 2048 2048
// 1024 1024
// 512 512