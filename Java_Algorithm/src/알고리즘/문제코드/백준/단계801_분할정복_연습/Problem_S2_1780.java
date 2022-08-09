package 알고리즘.문제코드.백준.단계801_분할정복_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S2_1780 {

    static int[][] rec;
    static int cnt1 = 0;
    static int cnt2 = 0;
    static int cnt3 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        rec = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                rec[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        check(0, 0, size);
        System.out.println(cnt1);
        System.out.println(cnt2);
        System.out.println(cnt3);

    }

    static int check(int x, int y, int step) {
        if (step == 1) {
            if (rec[x][y] == -1) {
                cnt1++;
            } else if (rec[x][y] == 0) {
                cnt2++;
            } else {
                cnt3++;
            }
            return rec[x][y];
        }

        int c1 = 0;
        int c2 = 0;
        int c3 = 0;
        for (int i = x; i < x + step; i += step / 3) {
            for (int j = y; j < y + step; j += step / 3) {
                int temp = check(i, j, step / 3);

                if(-1 == temp){
                    c1++;
                }else if(0 == temp){
                    c2++;
                }else if(1 == temp){
                    c3++;
                }
            }
        }

        if (c1 == 9) {
            cnt1 -= 8;
            return -1;
        } else if (c2 == 9) {
            cnt2 -= 8;
            return 0;
        } else if (c3 == 9) {
            cnt3 -= 8;
            return 1;
        } else {
            return -100;
        }
    }
}
