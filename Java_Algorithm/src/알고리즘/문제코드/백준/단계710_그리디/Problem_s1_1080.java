package 알고리즘.문제코드.백준.단계710_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_s1_1080 {

    static char[][] arr1;
    static char[][] arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr1 = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr1[i] = br.readLine().toCharArray();
        }

        arr2 = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr2[i] = br.readLine().toCharArray();
        }

        int count = 0;
        for (int i = 0; i < n-2; i++) {
            for (int j = 0; j < m-2; j++) {
                if(arr1[i][j] != arr2[i][j]){
                    swap(i, j);
                    count++;
                }
            }
        }

        boolean result = check_result(n, m);

        if(result){
            System.out.println(count);
        }else{
            System.out.println(-1);
        }

    }

    static void swap(int x, int y){
        for (int i = x; i < x+3; i++) {
            for (int j = y; j < y+3; j++) {
                arr1[i][j] = (arr1[i][j] == '0') ? '1' : '0';
            }
        }
    }

    static boolean check_result(int n, int m){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr1[i][j] != arr2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
}
