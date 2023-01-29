package 알고리즘.문제코드.백준.싸피_스터디.연습5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G3_1939 {

    static int result;
    static int start;
    static int des;

    static List<LinkedList<edge>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int island = Integer.parseInt(st.nextToken());
        int edge_cnt = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= island; i++) {
            graph.add(new LinkedList<edge>());
        }
        Set<Integer> weight_set = new TreeSet<>(Comparator.reverseOrder());
        for (int i = 0; i < edge_cnt; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int des = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(start)
                    .add(new edge(des, weight));
            graph.get(des)
                    .add(new edge(start, weight));
            weight_set.add(weight);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        des = Integer.parseInt(st.nextToken());

        for (int i : weight_set) {
            visited = new boolean[island + 1];
            visited[start] = true;
            if(dfs(start, i)) {
                System.out.println(result);
                break;
            }
        }
    }

    static boolean dfs(int v, int weight) {
        if (v == des) {
            result = Math.max(weight, result);
            return true;
        }

        for (edge e : graph.get(v)) {
            if (!visited[e.des] && weight <= e.weight) {
                visited[e.des] = true;
                if(dfs(e.des, weight)) return true;
            }
        }

        return false;
    }


    static class edge {
        int des;
        int weight;

        public edge(int des, int weight) {
            this.des = des;
            this.weight = weight;
        }
    }
}
