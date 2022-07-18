import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D2_1979 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            int result = 0;
            int[][] rec = new int[size][size];


            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    rec[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < size; i++) {
                int row = 0;
                int column = 0;

                for (int j = 0; j < size; j++) {
                    if (rec[i][j] == 1) {
                        row++;
                        if (j == size - 1 && length == row) {
                            result++;
                        }
                    } else if (rec[i][j] == 0) {
                        if (length == row) {
                            result++;
                        }
                        row = 0;
                    }

                    if (rec[j][i] == 1) {
                        column++;
                        if (j == size - 1 && length == column) {
                            result++;
                        }
                    } else if (rec[j][i] == 0) {
                        if (length == column) {
                            result++;
                        }
                        column = 0;
                    }
                }
            }

            System.out.println("#" + tc + " " + result);
        }

    }
}
