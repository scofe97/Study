package 알고리즘.문제코드.SSAFY.수업_220825;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrimTest_PQ {

    private static class Vertex {
        int no;
        int weight;
        Vertex next;

        public Vertex(int no, int weight, Vertex next) {
            this.no = no;
            this.weight = weight;
            this.next = next;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 인접 리스트
        Vertex[] adjList = new Vertex[V];  // 각 정점별 인접 리스트

        // input 파일에 주어진 인접 행렬 데이터를 adjMatrix에 담기
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // 무향이므로 간선 하나로 양방향 처리
            adjList[from] = new Vertex(to, weight, adjList[from]);
            adjList[to] = new Vertex(from, weight, adjList[to]);
        }

        // 프림 알고리즘에 필요한 자료구조
        int[] minEdge = new int[V];  // 각 정점 입장에서 신장트리에 포함된 정점과의 간선 비용 중 최소비용
        boolean[] visited = new boolean[V];  // 신장트리에 선택된 여부

        Arrays.fill(minEdge, Integer.MAX_VALUE);  // 최솟값 관리하기 위해 큰 값 세팅

        // 1. 임의의 시작점 처리, 0번 정점을 신장트리의 구성의 시작점
        minEdge[0] = 0;
        int result = 0;  // 최소신장트리 비용 누적

        /**
         * 추가
         */
        PriorityQueue<Vertex> pQueue = new PriorityQueue<>((v1, v2) -> v1.weight - v2.weight);
        pQueue.offer(new Vertex(0, minEdge[0], null));  // 큐에 들어가는 Vertex는 다음 Vertex에 대한 정보가 필요 없으므로 null

        /**
         * 변경
         */
        int cnt = 0;  // 신장트리에 추가된 정점 수
        while (!pQueue.isEmpty()) {

            // 1단계: 신장트리의 구성에 포함되지 않은 정점 중 최소비용 정점 선택
            Vertex minVertex = pQueue.poll();

            /**
             * 변경
             */
            if (visited[minVertex.no]) {
                continue;
            }

            // 2단계: 신장트리에 추가
            visited[minVertex.no] = true;
            result += minVertex.weight;

            /**
             * 추가 -> Queue에 남은값들이 이제 쓸모 없어지므로 버리기위한 코드
             * MST를 방문할때 모든 정점이 방문되었으면 더 이상 체크할 필요가 없기 때문
             */
            if (++cnt == V) {
                break;
            }

            // 3단계: 신장트리에 새롭게 추가되는 정점과 신장트리에 포함되지 않은 정점들의 기존 최소비용과 비교해서 갱신
            // 신장트리에 새롭게 추가되는 정점의 모든 인접정점 들여다보며 처리
            for (Vertex temp = adjList[minVertex.no]; temp != null; temp = temp.next) {
                if (!visited[temp.no] && minEdge[temp.no] > temp.weight) {
                    minEdge[temp.no] = temp.weight;
                    pQueue.offer(new Vertex(temp.no, minEdge[temp.no], null));  // 큐에 들어가는 Vertex는 다음 Vertex에 대한 정보가 필요 없으므로 null
                }
            }
        }

        System.out.println(result);
    }

}
