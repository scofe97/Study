package 알고리즘.문제코드.백준.싸피_스터디.연습1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem_B3_2445 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int size = Integer.parseInt(br.readLine());

        for (int i = (size-1) * -1; i < size; i++) {

            for (int j = 0; j < size - Math.abs(i); j++) {
                sb.append("*");
            }

            for (int j = 0; j < Math.abs(i)*2; j++) {
                sb.append(" ");
            }

            for (int j = 0; j < size - Math.abs(i); j++) {
                sb.append("*");
            }

            sb.append("\n");
        }
        System.out.println(sb);
    }
}
