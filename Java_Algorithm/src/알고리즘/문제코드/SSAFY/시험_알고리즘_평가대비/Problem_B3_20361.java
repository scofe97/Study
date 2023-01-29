package 알고리즘.문제코드.SSAFY.시험_알고리즘_평가대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_B3_20361 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int point = Integer.parseInt(st.nextToken());
        int change = Integer.parseInt(st.nextToken());

        int[] list = new int[size+1];
        list[point] = 1;
        for (int i = 0; i < change; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            swap(list,p1,p2 );
        }

        for (int i = 0; i < size+1; i++) {
            if(list[i] == 1) System.out.println(i);
        }

    }

    public static void swap(int[] list , int i1, int i2){
        int temp = list[i1];
        list[i1] = list[i2];
        list[i2] = temp;
    }
}
