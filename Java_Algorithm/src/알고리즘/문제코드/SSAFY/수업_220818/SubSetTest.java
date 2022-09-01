package 알고리즘.문제코드.SSAFY.수업_220818;

import java.util.Scanner;

// n개의 자연수를 입력받고 목표합이 주어지면 해당하는 부분집합을 출력한다.
public class SubSetTest {

    static int N, totalCnt, S;
    static int[] input;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        totalCnt = 0;
        input = new int[N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        subset(0, 0);
        System.out.println("총 경우의 수 " + totalCnt);
    }

    private static void subset(int index, int sum) {

        if (sum == S) {
            totalCnt++;
            for (int i = 0; i < N; i++) {
                System.out.print(isSelected[i] ? input[i] : "X");
                System.out.print("\t");
            }
            System.out.println();
            return;
        }

        if(sum > S || index == N) return;

        // 원소 선택
        isSelected[index] = true;
        subset(index + 1, sum + input[index]);

        // 원소 미선택
        isSelected[index] = false;
        subset(index + 1, sum);
    }
}
