package 알고리즘.문제코드.SSAFY.수업_220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_G4_1197 {

    static class Node{
        int vertex, weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int result = 0;

        Node[] adjList = new Node[V+1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무향처리
            adjList[from] = new Node(to, weight, adjList[from]);
            adjList[to] = new Node(from, weight, adjList[to]);
        }

        int[] minEdge = new int[V+1];
        boolean[] visited = new boolean[V+1];

        Arrays.fill(minEdge, Integer.MAX_VALUE);

        // chapter 1. 임의의 시작점 처리 ( 0번 정점을 시작으로 잡음 )
        minEdge[1] = 0;

        for (int c = 0; c < V; c++) {
            int min = Integer.MAX_VALUE;
            int minVertex = -1;

            for (int i = 1; i <= V; i++) {
                if (!visited[i] && min > minEdge[i]) {
                    min = minEdge[i];
                    minVertex = i;
                }
            }

            // chapter 2. 신장트리에 추가
            visited[minVertex] = true;
            result += min;


            // chapter 3. 신장트리에 새롭게 추가되는 정점과 포함되지 않은 정점들의 기존 최소비용과 비교해 갱신
            //  신장트리에 새롭게 추가되는 정점의 모든 인접정점을 들여다보며 정리
            for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
                if (!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
                    minEdge[temp.vertex] = temp.weight;
                }
            }
        }
        System.out.println(result);
    }
}
