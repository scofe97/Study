package 알고리즘.문제코드.SSAFY.수업_220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G4_1753 {
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

            edge.get(s).add(new Node(e, w));
        }

        // chapter 1. 우선순위 큐 생성
        PriorityQueue<Node> pq = new PriorityQueue<>((Node n1, Node n2) -> n1.weight - n2.weight);
        int[] distance = new int[V + 1];
        boolean[] visited = new boolean[V + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        // hint 최초 정점을 우선순위 큐에 넣어줌
        pq.offer(new Node(start, distance[start]));

        while(!pq.isEmpty()){
            // 처음 start
            Node node = pq.poll();
            int min = node.weight;
            int minIdx = node.idx;

            // 방문처리
            visited[minIdx] = true;
            for (Node node1 : edge.get(minIdx)) {

                // 해당 정점에서 갈 수 있는 간선들을 모두 뽑음
                // 만약 방문하지 않았고, 시작지점에서 거리가, 해당 정점을 거치는 것보다 길면 진행
                if(!visited[node1.idx] && distance[node1.idx] > min + node1.weight){

                    // 시작 지점에서 해당 정점의 거리를 초기화해줌
                    // 그 거리와 정점을 우선순위 큐에 넣는다.
                    distance[node1.idx] = min + node1.weight;
                    pq.offer(new Node(node1.idx, min + node1.weight));
                }
            }
        }

        for (int i = 1; i <= V ; i++) {
            System.out.println(distance[i] == Integer.MAX_VALUE ? "INF": distance[i]);
        }
    }


    static class Node {
        int idx; // 도착하는 정점
        int weight; // 정점으로 이동하는 비용
        int[] load;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }
}
