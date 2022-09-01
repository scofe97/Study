package 알고리즘.문제코드.SSAFY.수업_220819;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static StringBuilder sb;
    private static int N;  // 정점의 개수
    private static int M;  // 간선의 개수
    private static Edge[] edgeList;  // 간선 리스트
    private static boolean[] visited;  // DFS에서 사용할 방문여부 체크 배열

    public static void main(String[] args) throws Exception {

        /**
         * 0. 입력파일 읽어들이기
         */
        System.setIn(new FileInputStream("input1.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        // 결과를 한 번에 출력하기 위한 StringBuilder
        sb = new StringBuilder();

        /**
         * 1. 입력 파일 객체화
         */
        String[] split = in.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        int V = Integer.parseInt(split[2]);  // 시작할 정점 번호

        edgeList = new Edge[M];
        for (int i = 0; i < M; i++) {
            split = in.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);

            edgeList[i] = new Edge(from, to);
        }

        /**
         * 2. 알고리즘 풀기
         */
        visited = new boolean[N + 1];  // 인덱스 번호 1번부터 사용
        dfs(V);
        sb.append("\n");

        bfs(V);
        sb.append("\n");

        /**
         * 3. 정답 출력
         */


        System.out.println(sb);
    }

    private static void dfs(int current) {

        visited[current] = true;
        sb.append(current + " ");

        // current 정점의 인접정점들 처리
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {  // 방문하지 않았으며 인접한 경우
                for (Edge edge : edgeList) {

                    // 문제에서 간선은 양방향이라고 주어졌기 때문에 아래와 같이 확인
                    if ((edge.from == current && edge.to == i)
                            || (edge.from == i && edge.to == current)) {

                        dfs(i);
                        break;
                    }
                }
            }
        }
    }

    // start: 시작 정점 번호 (인덱스 번호)
    private static void bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean visited[] = new boolean[N + 1];

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {

            int current = queue.poll();
            sb.append(current).append(" ");

            // current 정점의 인접정점들에 큐에 넣어서 차후 탐색하도록 만들기
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {  // 방문하지 않았으며 인접한 경우

                    for (Edge edge : edgeList) {

                        // 문제에서 간선은 양방향이라고 주어졌기 때문에 아래와 같이 확인
                        if ((edge.from == current && edge.to == i)
                                || (edge.from == i && edge.to == current)) {

                            visited[i] = true;
                            queue.offer(i);
                            break;
                        }
                    }
                }
            }
        }
    }
}

class Edge {

    public int from;  // 진입 정점 번호
    public int to;  // 진출 정점 번호

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Edge [from=" + from + ", to=" + to + "]";
    }

}
