package 알고리즘.문제코드.SSAFY.수업_220819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem_순열 {

    static int[] numbers;
    static boolean[] visited;
    static int N;
    static int R;

    public static void main(String[] args) throws IOException {

        // R개의 숫자에서 N개의 숫자를 뽑음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

        visited = new boolean[N+1];
        numbers = new int[R];

        dfs(0,0);

    }

    static void dfs(int t, int step){
        if(step == R){
            System.out.println(Arrays.toString(numbers));
        }else{
            for (int i = t; i <= N; i++) {
                if(!visited[i]){
                    visited[i] = true;
                    numbers[step] = i;
                    dfs(i+1,step+1);
                    visited[i] = false;
                }
            }
        }
    }
}
