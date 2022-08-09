package 알고리즘.문제코드.SSAFY.live;

import java.util.Scanner;

public class Problem_부분집합 {

    static int N,totalCnt;
    static int[] input;
    static boolean[] isSelected;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        totalCnt = 0;
        input = new int[N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        subset(0);
    }

    private static void subset(int index){
        if(index == N){
            for (int i = 0; i < isSelected.length; i++) {
                System.out.print(isSelected[i] ? input[i]:"x");
                System.out.print("\t");
            }
            System.out.println();
            return;
        }
        // 원소 선택
        isSelected[index] = true;
        subset(index+1);

        // 원소 미선택
        isSelected[index] = false;
        subset(index+1);
    }
}
