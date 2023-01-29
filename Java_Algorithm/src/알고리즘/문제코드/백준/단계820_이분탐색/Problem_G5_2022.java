package 알고리즘.문제코드.백준.단계820_이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_G5_2022 {

    static double x;
    static double y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());

        double l = 0;
        double m = Math.min(x, y);

        while(m-l > 0.001){

            double k = (l+m)/2;
            double c0 = k * c / Math.sqrt(y*y - k*k);

            if(g(c0, k) > c){
                l = k;
            }else{
                m = k;
            }
        }

        System.out.format("%.3f", l);
    }


    static double g(double c0, double k){
        double A = Math.sqrt(x*x - k*k);
        return A - (A*c0 / k);
    }
}
