package 알고리즘.문제코드.사전학습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D2_1974 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st;

            int[][] rec = new int[9][9];

            for (int i = 0; i < 9; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 9; j++) {
                    rec[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = check(rec, 9);
            System.out.println("#" + tc + " " + result);
        }
    }

    public static int check(int[][] rec, int size) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                for (int k = 0; k < 9; k++) {
                    if (j != k && rec[i][j] == rec[i][k]) {
                        return 0;
                    }

                    if (i != k && rec[i][j] == rec[k][j]) {
                        return 0;
                    }
                }
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if ((((3 * (i / 3)) + k) != i && ((3 * (j / 3)) + l) != j) && rec[i][j] == rec[(3 * (i / 3)) + k][(3 * (j / 3)) + l]) {
                            return 0;
                        }
                    }
                }
            }
        }

        return 1;
    }
}