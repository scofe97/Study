package 알고리즘.문제코드.백준.단계810_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_S5_2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int cnt = Integer.parseInt(br.readLine());
        int[] list = new int[cnt];

        for (int i = 0; i < cnt; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(list);

        for (int i = 0; i < cnt; i++) {
            System.out.println(list[i]);
        }

    }
}
