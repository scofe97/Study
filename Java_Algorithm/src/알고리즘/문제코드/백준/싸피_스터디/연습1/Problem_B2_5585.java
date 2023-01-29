package 알고리즘.문제코드.백준.싸피_스터디.연습1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_B2_5585 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int money = 1000 - Integer.parseInt(br.readLine());
        int[] money_list = {500, 100, 50, 10 ,5, 1};
        int count = 0;

        for (int i = 0; i < money_list.length; i++) {

            count += money / money_list[i];
            money %= money_list[i];
        }

        System.out.println(count);
    }
}
