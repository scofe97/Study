package 알고리즘.문제코드.이론._0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1806 {

    static int N, S;
    static int[] line, sum_line;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] s;

        s = br.readLine()
                .split(" ");
        N = Integer.parseInt(s[0]);
        S = Integer.parseInt(s[1]);


        s = br.readLine()
                .split(" ");
        line = new int[N];
        sum_line = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(s[i]);
            line[i] = temp;

            sum += temp;
            sum_line[i] = sum;
        }

        System.out.println(twoPointer());
    }

    private static int twoPointer(){
        int left = 0;
        int right = 0;
        int sum = 0;

        int result = Integer.MAX_VALUE;
        while (left <= right) {

            if(sum >= S){
                sum -= line[left++];
                result = Math.min(result, right - left + 1);
            }else{
                if(right >= N) break;
                sum += line[right++];
            }
        }

        return result != Integer.MAX_VALUE ? result : 0;
    }
}
