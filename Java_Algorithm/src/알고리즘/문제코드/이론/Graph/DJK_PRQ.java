package 알고리즘.문제코드.이론.Graph;


import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node> {
    private int index;
    private int distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return this.index;
    }

    public int getDistance() {
        return this.distance;
    }

    // 우선순위 큐에 넣을 때 거리를 기준으로 오름차순 정렬
    @Override
    public int compareTo(Node other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}

public class DJK_PRQ {
    public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
    public static int n, m, start;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
    public static int[] d = new int[100001];

    public static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.getDistance();
            int now = node.getIndex();
            if (d[now] < dist) continue;
            for (int i = 0; i < graph.get(now)
                    .size(); i++) {
                int cost = d[now] + graph.get(now)
                        .get(i)
                        .getDistance();
                if (cost < d[graph.get(now)
                        .get(i)
                        .getIndex()]) {
                    d[graph.get(now)
                            .get(i)
                            .getIndex()] = cost;
                    pq.offer(new Node(graph.get(now)
                            .get(i)
                            .getIndex(), cost));
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 노드의 개수, 간선의 개수, 시작 노드 번호를 입력받기
        n = sc.nextInt();
        m = sc.nextInt();
        start = sc.nextInt();

        // 그래프와 최단 거리 테이블을 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Node>());
            d[i] = INF;
        }

        // 모든 간선 정보를 입력받기
        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            // a번 노드에서 b번 노드로 가는 비용이 c라는 의미
            graph.get(a)
                    .add(new Node(b, c));
        }

        dijkstra(start);

        // 모든 노드로 가기 위한 최단 거리를 출력
        for (int i = 1; i <= n; i++) {
            if (d[i] == INF) {
                System.out.println("INFINITY");
            } else {
                System.out.println(d[i]);
            }
        }
    }
}

