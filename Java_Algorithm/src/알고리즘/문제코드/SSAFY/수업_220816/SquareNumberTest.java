package 알고리즘.문제코드.SSAFY.수업_220816;

import java.io.IOException;
import java.util.Scanner;

public class SquareNumberTest {
    static int callCnt1, callCnt2;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int n = sc.nextInt();

        System.out.println(exp1(x,n));
        System.out.println(callCnt1);

        System.out.println(exp2(x,n));
        System.out.println(callCnt2);

    }

    static long exp1(long x, long n) {
        callCnt1++;
        if (n == 1) return x;
        return x * exp1(x, n - 1);
    }

    static long exp2(long x, long n) {
        callCnt2++;
        if(n==1) return x;
        long y = exp2(x, n / 2);

        return n % 2 == 0 ? y * y :y * y * x;
    }
}

/*
2 20
1048576
20
1048576
5

1073741824
30
1073741824
5
 */
