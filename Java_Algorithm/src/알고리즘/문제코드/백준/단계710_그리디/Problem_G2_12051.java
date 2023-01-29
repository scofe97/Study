package 알고리즘.문제코드.백준.단계710_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_G2_12051 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());

        int[] lis = new int[size];
        int[] seq = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < size; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        lis[0] = seq[0];
        int lengthOfLIS = 1;

        for (int i = 1; i < size; i++) {

            // i번쨰 값
            int key = seq[i];

            // 이전값보다 key 값이 더크면
            if(lis[lengthOfLIS-1] < key){
                // 큰 값을 끝에 넣음
                lengthOfLIS++;
                lis[lengthOfLIS - 1] = key;
            }else{
                // 만약 크지않다면? -> 대치

                // 0과 lis 길이
                int lo = 0;
                int hi = lengthOfLIS;

                while(lo < hi){
                    int mid = (lo + hi)/2;

                    if(lis[mid] < key){
                        lo = mid + 1;
                    }else{
                        hi = mid;
                    }

                }

                lis[lo] = key;
            }
        }

        System.out.println(lengthOfLIS);
    }
}
