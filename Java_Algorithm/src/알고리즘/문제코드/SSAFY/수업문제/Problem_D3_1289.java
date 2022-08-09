package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_D3_1289 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            char[] temp = br.readLine().toCharArray();
            int cnt = 0;

            for (int j = 0; j < temp.length; j++) {

                if (temp[j] != '0') {
                    cnt++;
                    for (int k = j; k < temp.length; k++) {
                        temp[k] = temp[k] == '0' ? '1' : '0';
                    }
                }
            }

            System.out.println("#" + tc + " " + cnt);
        }
    }
}
