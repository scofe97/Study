package 알고리즘.문제코드.개인SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D3_14178 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int size = 2*d+1;
            int result;
            if(n % size > 0){
                result = n/size + 1;
            }else{
                result = n/size;
            }

            System.out.println("#" + tc + " " + result);
        }
    }
}