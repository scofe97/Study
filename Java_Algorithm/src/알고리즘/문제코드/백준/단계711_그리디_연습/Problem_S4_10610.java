package 알고리즘.문제코드.백준.단계711_그리디_연습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_S4_10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] num = br.readLine().toCharArray();

        for (int i = 0; i < num.length; i++) {
            for (int j = i; j < num.length ; j++) {
                if(num[i] < num[j]){
                    char temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }

        int num_int = Integer.parseInt(String.valueOf(num));

        int result = num_int % 30 == 0 ? num_int : -1;
        System.out.println(result);

    }
}
