package 자바공부.유튜브_라이브;

import java.util.Arrays;
import java.util.Scanner;

public class Ex_Bitmasking {

    static int N, R, totalCnt;
    static int[] numbers, input;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        totalCnt = 0;

        input = new int[N];
        numbers = new int[R];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        perm(0, 0);
    }

    private static void perm(int cnt, int flag) {
        if(cnt == R){
            totalCnt++;
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = 0; i < N; i++) {
            if( (flag & 1 << i) == 0){
                numbers[cnt] = input[i];
                perm(cnt + 1, flag | 1 << i);
            }
        }
    }
}
