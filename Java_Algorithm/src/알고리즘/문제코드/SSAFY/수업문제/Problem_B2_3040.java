package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_B2_3040 {

    static int[] num;
    static Stack<Integer> check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        num = new int[9];
        check = new Stack<>();

        for (int i = 0; i < 9; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        dfs(0,0,0);
    }

    static void dfs(int step, int idx, int sum){
        if(step == 7 ){
            if(sum == 100){
                for (int i : check) {
                    System.out.println(num[i]);
                }
            }
            return;
        }

        for (int i = idx; i < 9; i++) {
            check.push(i);
            dfs(step+1,i+1, sum + num[i]);
            check.pop();
        }
    }
}
