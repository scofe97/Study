package 알고리즘.문제코드.SSAFY.수업_221006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_3307 {

    static int n;
    static int[] arr;
    static int[] lis;
    static int[] V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());

            arr = new int[n];
            lis = new int[n];
            V = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int result = dp();
            System.out.println("#" + tc + " " + lis[result]);
            printLis(result);
        }
    }

    private static int dp(){
        int maxLis = 0;
        int last = 0;

        for (int i = 0; i < n; i++) {
            lis[i] = 1;
            V[i] = -1;

            for (int j = i-1; j >= 0; j--) {
                if(arr[i] > arr[j] && lis[i] <= lis[j]){
                    lis[i] = lis[j] + 1;
                    V[i] = j;
                }

                if(maxLis < lis[i]){
                    maxLis = lis[i];
                    last = i;
                }
            }
        }

        return last;
    }


    private static void printLis(int idx) {

        if(V[idx] == -1) {
            System.out.print(arr[idx] + " ");
            return;
        }
        printLis(V[idx]);
        System.out.print(arr[idx] + " ");
    }
}
