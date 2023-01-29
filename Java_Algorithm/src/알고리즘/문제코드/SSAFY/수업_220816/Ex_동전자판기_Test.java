package 알고리즘.문제코드.SSAFY.수업_220816;

import java.io.IOException;
import java.util.Scanner;

public class Ex_동전자판기_Test {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int targetMoney = sc.nextInt();

        int[] units = {500, 100, 50, 10, 5, 1};
        int[] counts = {sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt()};

        int totalMoney = 0;
        for (int i = 0; i < units.length; i++) {
            totalMoney += units[i] * counts[i];
        }

        int remainMoney = totalMoney - targetMoney;

        int sum = 0, maxCnt, availCnt;
        for (int i = 0; i < units.length; i++) {
            maxCnt = remainMoney / units[i];
            availCnt = Math.min(maxCnt, counts[i]);

            counts[i] -= availCnt;
            remainMoney -= availCnt * units[i];

            sum += counts[i];
        }

        System.out.println(totalMoney);
        System.out.println(sum);

        for (int i = 0; i < counts.length; i++) {
            System.out.println(counts[i] + " ");
        }
    }
}

// 1750 -> 750 -> 50 -> 0