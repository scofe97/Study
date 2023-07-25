package 알고리즘.문제코드.이론._0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _2003 {

    static int N;
    static int M;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] s;

        s = br.readLine()
                .split(" ");

        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);

        s = br.readLine()
                .split(" ");

        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list_sum = new ArrayList<>();
        int sum = 0;
        for (String s1 : s) {
            int temp = Integer.parseInt(s1);
            list.add(temp);

            sum += temp;
            list_sum.add(sum);
        }

        for (int i = 0; i < N; i++) {
            if(list_sum.get(i) == M) {
                result++;
                continue;
            }

            for (int j = 0; j < i; j++) {
                if(list_sum.get(i) - list_sum.get(j) == M){
                    result++;
                    break;
                }
            }
        }


        System.out.println(result);
    }
}
