package 알고리즘.문제코드.이론._0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1940 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] s;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int result = 0;
        int[] list = new int[N];
        s = br.readLine()
                .split(" ");
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(s[i]);
        }

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (list[i] + list[j] == M) result++;
            }
        }

        System.out.println(result);
    }
}
