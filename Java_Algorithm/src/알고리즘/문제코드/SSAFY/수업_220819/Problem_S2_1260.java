package 알고리즘.문제코드.SSAFY.수업_220819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/status?user_id=sdh9615&problem_id=1260&from_mine=1
public class Problem_S2_1260 {
    static int v;
    static int e;
    static boolean[] visited;
    static List<ArrayList<Integer>> list;

    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        int first = Integer.parseInt(st.nextToken());
        visited = new boolean[v+1];

        list = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            list.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.get(start).add(end);
            list.get(end).add(start);
        }

        for (int i = 0; i <= v; i++) {
            Collections.sort(list.get(i));
        }

        dfs(first);
        sb.append("\n");
        bfs(first);
        System.out.println(sb);
    }

    static void dfs(int v){
        visited[v] = true;
        sb.append(v).append(" ");

        for (Integer integer : list.get(v)) {
            if(!visited[integer]) dfs(integer);
        }
    }

    static void bfs(int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited = new boolean[v+1];
        visited[start] = true;

        while(!queue.isEmpty()){
            int temp = queue.poll();
            sb.append(temp).append(" ");
            for (Integer integer : list.get(temp)) {
                if(!visited[integer]){
                    visited[integer] = true;
                    queue.offer(integer);
                }
            }
        }
    }
}
