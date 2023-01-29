package 알고리즘.문제코드.백준.싸피_스터디.연습1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 대략 40분
public class Problem_G5_2448 {

    static char[][] arr;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        size = Integer.parseInt(br.readLine());
        arr = new char[size][size * 2 - 1];

        recursive(0, (size * 2 - 1) / 2, size);

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size * 2 - 1; j++) {
                if (arr[i][j] != '*') {
                    sb.append(' ');
                } else {
                    sb.append(arr[i][j]);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void recursive(int x, int y, int s) {
        if (s != 3) {
            int k = s/2;
            recursive(x + (s / 2), y - k, s / 2);
            recursive(x, y, s / 2);
            recursive(x + (s / 2), y + k, s / 2);
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j <= i; j++) {
                    if(i == 1 && j == 0){
                        continue;
                    }
                    arr[x + i][y + j] = '*';
                    arr[x + i][y - j] = '*';
                }
            }
        }
    }
}
