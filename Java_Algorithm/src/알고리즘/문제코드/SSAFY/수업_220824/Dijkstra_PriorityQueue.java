package 알고리즘.문제코드.SSAFY.수업_220824;

import java.util.PriorityQueue;

public class Dijkstra_PriorityQueue {

    public static void main(String[] args) {
        Graph g = new Graph(6); // 노드 수 만큼 그래프 생성

        // 시작, 끝, 간선 가중치 입력
        g.input(0, 1, 7);
        g.input(0, 2, 9);
        g.input(0, 5, 14);
        g.input(1, 2, 10);
        g.input(1, 3, 15);
        g.input(2, 3, 11);
        g.input(2, 5, 2);
        g.input(3, 4, 6);
        g.input(4, 5, 9);

        // 시작노드 기준 다익스트라 알고리즘 실행
        g.dijkstra(0);
    }

    static class Graph {
        private int n;           // 노드들의 수
        private int maps[][];    // 노드들간의 가중치 저장할 변수

        public Graph(int n) {
            this.n = n;
            maps = new int[n][n];

            // 인접행렬 모든 값 무한대로 초기화
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    maps[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        static class Node implements Comparable<Node> {
            private int weight;
            private int index;

            public Node(int weight, int index) {
                this.weight = weight;
                this.index = index;
            }

            @Override
            public int compareTo(Node node) {
                return Integer.compare(this.weight, node.weight);
            }
        }

        public void input(int i, int j, int w) {
            maps[i][j] = w;
            maps[j][i] = w;
        }

        public void dijkstra(int v) {
            PriorityQueue<Node> pq = new PriorityQueue<>();     // 노드까지의 거리를 저장할 우선순위 큐
            int distance[] = new int[n];          // 최단 거리를 저장할 변수
            boolean[] check = new boolean[n];     // 해당 노드를 방문했는지 체크할 변수

            // distance값 초기화. 무한대를 int 자료형의 최대값으로 표현했다.
            for(int i=0; i<n; ++i){
                distance[i] = Integer.MAX_VALUE;
            }

            pq.offer(new Node(v, 0));
            check[v] = true;
            distance[v] = 0;

            // 결과값 출력
            for(int i=0; i<n; ++i){
                if(distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
                else System.out.print(distance[i]+" ");
            }
            System.out.println();

            // 연결노드 distance 갱신
            for (int i = 0; i < n; ++i) {
                if (!check[i] && maps[v][i] != Integer.MAX_VALUE) {
                    distance[i] = maps[v][i];
                    pq.add(new Node(maps[v][i], i));
                }
            }

            // 결과값 출력
            for(int i=0; i<n; ++i){
                if(distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
                else System.out.print(distance[i]+" ");
            }
            System.out.println();

            while(!pq.isEmpty()){
                int min = Integer.MAX_VALUE;
                int minIdx = -1;

                Node node = pq.poll();
                min = node.weight;
                minIdx = node.index;

                check[minIdx] = true;
                for (int i = 0; i < n; i++) {
                    if(!check[i] && maps[minIdx][i] != Integer.MAX_VALUE && distance[i] > maps[minIdx][i] + min){
                        distance[i] = maps[minIdx][i] + distance[minIdx];
                        pq.offer(new Node(distance[i], i));
                    }
                }

                // 결과값 출력
                for(int i=0; i<n; ++i){
                    if(distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
                    else System.out.print(distance[i]+" ");
                }
                System.out.println();
            }
        }
    }
}

