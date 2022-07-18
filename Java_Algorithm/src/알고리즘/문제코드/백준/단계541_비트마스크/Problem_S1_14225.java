package 알고리즘.문제코드.백준.단계541_비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S1_14225 {

    static int result;
    static int size;
    static boolean[] visited;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        visited = new boolean[size];
        arr = new int[size];
        result = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            int temp = Integer.parseInt(st.nextToken());
            arr[i] = temp;
            result += temp;
        }

        visited = new boolean[result+2];


        dfs(0, 0);
        for (int i = 0; i < result+2; i++) {
            if(!visited[i]){
                System.out.println(i);
                return;
            }
        }

    }

    static void dfs(int step, int sum){

        if(step == size){
            visited[sum] = true;
            return;
        }

        dfs(step + 1, sum);
        dfs(step + 1, sum + arr[step]);
    }
}
