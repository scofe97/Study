package 알고리즘.문제코드.백준.단계800_분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_S5_10815 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int size1 = Integer.parseInt(br.readLine());
        int[] list1 = new int[size1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size1; i++) {
            list1[i] = Integer.parseInt(st.nextToken());
        }

        int size2 = Integer.parseInt(br.readLine());
        int[] list2 = new int[size2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size2; i++) {
            list2[i] = Integer.parseInt(st.nextToken());
        }

        list1 = Arrays.stream(list1).sorted().toArray();

        boolean flag;
        int start;
        int end;
        int point;

        for (int i : list2) {
            flag = false;
            start = 0;
            end = list1.length - 1;

            while (start <= end) {
                point = (start + end) / 2;

                if (list1[point] == i) {
                    flag = true;
                    break;
                } else if (list1[point] > i) {
                    end = point - 1;
                } else if (list1[point] < i) {
                    start = point + 1;
                }
            }

            sb.append(flag ? "1 " : "0 ");
        }

        System.out.println(sb);

    }
}
