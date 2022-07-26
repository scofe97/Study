package 알고리즘.문제코드.백준.단계710_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_G1_2138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();

        int count = -1;
        int count1 = 0;
        int count2 = 1;

        if (size == 2) {
            int check = 0;
            for (int i = 0; i < 2; i++) {
                if (arr1[i] != arr2[i]) {
                    check++;
                }
            }

            if(check == 2){
                System.out.println(1);
            } else if (check == 1) {
                System.out.println(-1);
            }else {
                System.out.println(0);
            }
            return;
        }


        char[] arr1_1 = Arrays.copyOfRange(arr1, 0, size);
        for (int i = 0; i < size - 1; i++) {
            if (i == size - 2 && arr1_1[i] != arr2[i]) {
                arr1_1[i] = swap(arr1_1[i]);
                arr1_1[i + 1] = swap(arr1_1[i + 1]);
                count1++;
            } else if (arr1_1[i] != arr2[i]) {
                arr1_1[i] = swap(arr1_1[i]);
                arr1_1[i + 1] = swap(arr1_1[i + 1]);
                arr1_1[i + 2] = swap(arr1_1[i + 2]);
                count1++;
            }
        }

        if(arr1_1[size-1] != arr2[size-1]){
            count1 = Integer.MAX_VALUE;
        }

        char[] arr1_2 = Arrays.copyOfRange(arr1, 0, size);
        arr1_2[0] = swap(arr1_2[0]);
        arr1_2[1] = swap(arr1_2[1]);
        for (int i = 0; i < size - 1; i++) {
            if (i == size - 2 && arr1_2[i] != arr2[i]) {
                arr1_2[i] = swap(arr1_2[i]);
                arr1_2[i + 1] = swap(arr1_2[i + 1]);
                count2++;
            } else if (arr1_2[i] != arr2[i]) {
                arr1_2[i] = swap(arr1_2[i]);
                arr1_2[i + 1] = swap(arr1_2[i + 1]);
                arr1_2[i + 2] = swap(arr1_2[i + 2]);
                count2++;
            }
        }

        if(arr1_2[size-1] != arr2[size-1]){
            count2 = Integer.MAX_VALUE;
        }
        System.out.println(Math.min(count1, count2) == Integer.MAX_VALUE ? -1 : Math.min(count1, count2));

    }

    static char swap(char c) {
        if (c == '0') {
            return '1';
        } else {
            return '0';
        }
    }
}
