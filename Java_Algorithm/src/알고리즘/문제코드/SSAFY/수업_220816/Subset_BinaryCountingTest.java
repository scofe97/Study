package 알고리즘.문제코드.SSAFY.수업_220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Subset_BinaryCountingTest {
    
    static int[] numbers;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
            
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        generateSubset();
    }
    
    private static void generateSubset(){
        for (int flag = 0, caseCnt = 1 << N; flag < caseCnt; flag++) {

            for (int i = 0; i < N; i++) {
                if((flag & (1 << i)) != 0 ){
                    System.out.print(numbers[i] + " ");
                }
            }
            System.out.println();
        }
    }
}
