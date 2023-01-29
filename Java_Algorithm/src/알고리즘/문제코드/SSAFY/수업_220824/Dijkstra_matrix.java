package 알고리즘.문제코드.SSAFY.수업_220824;

import java.io.IOException;

class Graph {
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

    public void input(int i, int j, int w) {
        maps[i][j] = w;
        maps[j][i] = w;
    }

    public void dijkstra(int v) {
        // hint 최단거리를 지정할 배열 및 노드 방문 여부 배열을 생성
        int[] distance = new int[n];
        boolean[] check = new boolean[n];

        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[v] = 0;
        check[v] = true;

        for (int i = 0; i < n; i++) {
            if(distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
            else System.out.print(distance[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            if(!check[i] && maps[v][i] != Integer.MAX_VALUE){
                distance[i] = maps[v][i];
            }
        }

        for (int i = 0; i < n; i++) {
            if(distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
            else System.out.print(distance[i] + " ");
        }
        System.out.println();

        for (int a = 0; a < n-1; a++) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;

            for (int i = 0; i < n; i++) {
                if(!check[i] && distance[i] < min){
                    min = distance[i];
                    minIdx = i;
                }
            }

            check[minIdx] = true;
            for (int i = 0; i < n; i++) {
                if(!check[i]
                        && maps[minIdx][i] != Integer.MAX_VALUE
                        && distance[minIdx] + maps[minIdx][i] < distance[i]){
                    distance[i] = distance[minIdx] + maps[minIdx][i];
                }
            }

            for (int i = 0; i < n; i++) {
                if(distance[i] == Integer.MAX_VALUE) System.out.print("∞ ");
                else System.out.print(distance[i] + " ");
            }
            System.out.println();

        }
    }
}


public class Dijkstra_matrix {
    public static void main(String[] args) throws IOException {
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
}
