package 알고리즘.문제코드.백준.싸피_스터디.연습6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_G4_1091{
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        int[] p = new int[N];
        int[] order = new int[N];
        int[] cards = new int[N];

        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++) p[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        for(int i=0;i<N;i++) {
            order[Integer.parseInt(st.nextToken())] = i;
            cards[i] = i%3;
        }

        int[] compare = cards.clone();
        int[] next = new int[N];

        int result = 0;
        while(!Arrays.equals(cards,p) && !(result !=0 && Arrays.equals(cards, compare))) {
            for(int j=0;j<N;j++) next[order[j]] = cards[j];

            cards = next.clone();
            result++;
        }

        if(result !=0 && Arrays.equals(cards, compare)) System.out.println(-1);
        else System.out.println(result);
    }
}
