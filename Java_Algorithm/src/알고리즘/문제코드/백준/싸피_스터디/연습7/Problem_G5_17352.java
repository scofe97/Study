package 알고리즘.문제코드.백준.싸피_스터디.연습7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Problem_G5_17352 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1; i<=n; i++) {
            parent[i] = i;
        }


        for (int i = 0; i < n - 2; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a,b);
        }

        HashSet<Integer> set = new HashSet<>();

        for(int i=1; i<=n; i++)
            set.add(find(i));

        for(int s : set)
            System.out.print(s+" ");
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a<b) parent[b] = a;
        else parent[a] = b;
    }

    static int find(int x) {
        if(parent[x] == x)
            return x;

        return find(parent[x]);
    }
}

