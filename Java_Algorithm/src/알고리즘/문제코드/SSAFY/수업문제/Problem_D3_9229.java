package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_D3_9229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= size; tc++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            int[] list = new int[cnt];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cnt; i++) {
                list[i] = Integer.parseInt(st.nextToken());
            }

            int[] sort = Arrays.stream(list).toArray();
            int max = -1;
            for (int j = sort.length-1; j >= 1; j--) {
                for (int k = j-1; k >= 0 ; k--) {
                    if(sort[j]+sort[k] > max && sort[j]+sort[k] <= weight){
                        max = sort[j]+sort[k];
                    }
                }
            }

            System.out.printf("#%d %d\n",tc,max);
        }
    }
}
