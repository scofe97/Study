package 알고리즘.문제코드.SSAFY.수업_220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G4_1753_previous {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        List<List<Node>> edge = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            edge.add(new LinkedList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edge.get(s).add(new Node(s, e, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2) -> n1.weight - n2.weight);
        int[] distance = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;
        visited[start] = true;

        for (Node node : edge.get(start)) {
            distance[node.end] = node.weight;
            pq.add(node);
        }
        // 결과값 출력
        for(int i=1; i<=V; ++i){
            if(distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
            else System.out.print(distance[i]+" ");
        }
        System.out.println();

        while(!pq.isEmpty()){
            Node node = pq.poll();
            int minIdx = node.end;
            visited[minIdx] = true;

            // 해당부분 참조
            for (Node node1 : edge.get(minIdx)) {
                if(!visited[node1.end]
                        && distance[node1.end] > distance[node1.start] + node1.weight){
                    distance[node1.end] = distance[node1.start] + node1.weight;
                    pq.offer(node1);
                }
            }
            // 결과값 출력
            for(int i=1; i<=V; ++i){
                if(distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
                else System.out.print(distance[i]+" ");
            }
            System.out.println();

        }

        for (int i = 1; i <= V ; i++) {
            System.out.println(distance[i] == Integer.MAX_VALUE ? "INF": distance[i]);
        }
    }


    static class Node {
        int start;
        int end;
        int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return start + " " + end + " " + weight;
        }
    }
}
