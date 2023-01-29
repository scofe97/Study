package 알고리즘.문제코드.백준.싸피_스터디.연습8;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_G5_1722 {

    static int n;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        long[] f = new long[21];
        boolean[] visited = new boolean[21];

        Arrays.fill(f, 1);

        for (int i = 1; i <= 20; i++) {
            f[i] = f[i - 1] * i;
        }

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int game = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        // 몇번째 인지 받은경우
        if (game == 1) {
            long k = Long.parseLong(st.nextToken());

            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= n; j++) {

                    if (visited[j]) continue;

                    if (k > f[n - i - 1]) {
                        k -= f[n - i - 1];
                    } else {
                        arr[i] = j;
                        visited[j] = true;
                        break;
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                sb.append(arr[i]).append(" ");
            }

        } else {
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long ans = 1;

            for (int i = 0; i < n; i++) {
                for (int j = 1; j < arr[i]; j++) {
                    if (visited[j]) continue;
                    ans += f[n - i - 1];
                }
                ans += f[n-i-1];
            }
            sb.append(ans);
        }

        System.out.println(sb.toString());
    }
}

