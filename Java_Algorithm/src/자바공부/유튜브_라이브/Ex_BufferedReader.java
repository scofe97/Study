package 자바공부.유튜브_라이브;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Ex_BufferedReader {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\study\\Java_Algorithm\\src\\자바공부\\유튜브_라이브\\inputTC.txt"));
        String str;
        StringBuilder sb = new StringBuilder();
        while((str = br.readLine()) != null){
            System.out.println(str);
        }

        long start = System.nanoTime();
        BufferedReader br2 = new BufferedReader(new FileReader("D:\\study\\Java_Algorithm\\src\\자바공부\\유튜브_라이브\\inputTC.txt"));

        int TC = Integer.parseInt(br2.readLine());
        for (int tc = 0; tc < TC; tc++) {
            int N = Integer.parseInt(br2.readLine());

            int sum = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br2.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    sum += Integer.parseInt(st.nextToken());
                }
            }
            sb.append("#").append(tc).append(" ").append(sum).append("\n");
        }
        long end = System.nanoTime();
        sb.append((end - start) / 1_000_000_000.0).append("s");
        System.out.println(sb);


    }
}
