
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D2_2001 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int max = 0;

            int[][] rec = new int[N][N];
            int mn = N - M + 1;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    rec[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int c = 0; c < mn; c++) {
                for (int r = 0; r < mn; r++) {
                    int tmp = 0;

                    for (int c2 = 0; c2 < M; c2++) {
                        for (int r2 = 0; r2 < M; r2++) {
                            tmp += rec[c + c2][r + r2];
                        }
                    }

                    max = Math.max(max, tmp);
                }
            }

            System.out.println("#" + tc + " " + max);
        }

    }
}
