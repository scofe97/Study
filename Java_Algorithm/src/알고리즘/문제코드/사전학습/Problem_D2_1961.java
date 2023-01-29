package 알고리즘.문제코드.사전학습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D2_1961 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int size = Integer.parseInt(st.nextToken());
            String[][] rec = new String[size][size];

            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < size; j++) {
                    rec[i][j] = st.nextToken();
                }
            }

            String[][] rec_90 = Rotation(rec, size);
            String[][] rec_180 = Rotation(rec_90, size);
            String[][] rec_270 = Rotation(rec_180, size);

            System.out.printf("#%d\n", tc);
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.out.print(rec_90[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < size; j++) {
                    System.out.print(rec_180[i][j]);
                }
                System.out.print(" ");
                for (int j = 0; j < size; j++) {
                    System.out.print(rec_270[i][j]);
                }
                System.out.println();
            }

        }
    }
    public static String[][] Rotation(String[][] arr,int n){
        String[][] temp_arr = new String[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                temp_arr[i][j] = arr[n-1-j][i];
            }
        }

        return temp_arr;
    }
}
