import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_D2_1959 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] a = new int[N];
            int[] b = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                b[j] = Integer.parseInt(st.nextToken());
            }

            int max = 0;
            int tmp = 0;

            int size_big = Math.max(M,N);
            int size_small = Math.min(M,N);

            for (int j = 0; j <= size_big - size_small; j++) {
                int sum = 0;

                for (int i = 0; i < size_small; i++) {
                    if(size_small == N){
                        tmp = a[i] * b[j+i];
                    }else{
                        tmp = b[i] * a[j+i];
                    }
                    sum += tmp;
                }

                max = Math.max(max,sum);
            }

            System.out.println("#"+tc+" "+max);
        }
    }
}
