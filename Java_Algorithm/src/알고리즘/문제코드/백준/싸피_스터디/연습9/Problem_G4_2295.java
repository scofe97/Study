package 알고리즘.문제코드.백준.싸피_스터디.연습9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_G4_2295 {

    static List<Integer> list;
    static List<Integer> sum;

    static boolean binarySearch(int t){
        int l = 0;
        int r = sum.size() - 1;

        while (l < r) {
            int m = (l + r) / 2;

            if (sum.get(m) > t) {
                r = m - 1;
            }
            else if (sum.get(m) < t) {
                l = m + 1;
            }
            else return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        sum = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum.add(list.get(i) + list.get(j));
            }
        }

        Collections.sort(list);
        Collections.sort(sum);

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (binarySearch(list.get(i) - list.get(j))) {
                    System.out.println(list.get(i));
                    return;
                }
            }
        }
    }
}
