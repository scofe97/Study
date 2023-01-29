package 알고리즘.문제코드.SSAFY.시험_A형대비.위상정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G3_2252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        boolean[] check = new boolean[v+1];

        int[] degree = new int[v+1];
        List<List<Integer>> edgeList = new ArrayList<>();
        for (int i = 0; i < v+1; i++) {
            edgeList.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            edgeList.get(start).add(end);
            degree[end]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < v+1; i++) {
            if(degree[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()){
            int temp = queue.poll();
            check[temp] = true;
            sb.append(temp).append(" ");

            for (Integer i : edgeList.get(temp)) {
                if(--degree[i] == 0) queue.offer(i);
            }
        }

        System.out.println(sb);

    }
}
