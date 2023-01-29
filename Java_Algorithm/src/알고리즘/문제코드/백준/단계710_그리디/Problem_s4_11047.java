package 알고리즘.문제코드.백준.단계710_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_s4_11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int type = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());

        int count = 0;
        int my_money = 0;

        int[] moneyList = new int[type];
        for (int i = 0; i < type; i++) {
            moneyList[i] = Integer.parseInt(br.readLine());
        }

        int idx = type-1;
        while(money != 0){
            count += money/moneyList[idx];
            money %= moneyList[idx];
            idx--;
        }

        System.out.println(count);
    }
}
