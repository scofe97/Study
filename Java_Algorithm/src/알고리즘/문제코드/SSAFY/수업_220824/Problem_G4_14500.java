package 알고리즘.문제코드.SSAFY.수업_220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G4_14500 {
    static int[][] graph;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int n;
    static int m;
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = -1;
        graph = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, graph[i][j]);
                visited[i][j] = false;
                check(i, j);
            }
        }

        System.out.println(result);
    }

    static void dfs(int y, int x,int step, int sum) {
        if (step == 4) {
            result = Math.max(result, sum);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int ny = y + dy[k];
            int nx = x + dx[k];

            if (ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx]) {
                continue;
            }

            visited[ny][nx] = true;
            dfs(ny, nx,step+1, sum + graph[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    static void check(int y, int x) {
        if (y < n - 2 && x < m - 1)
            result = Math.max(result, graph[y][x] + graph[y + 1][x] + graph[y + 2][x] + graph[y + 1][x + 1]);

        if (y < n - 2 && x > 0)
            result = Math.max(result, graph[y][x] + graph[y + 1][x] + graph[y + 2][x] + graph[y + 1][x - 1]);

        if (y < n - 1 && x < m - 2)
            result = Math.max(result, graph[y][x] + graph[y][x + 1] + graph[y][x + 2] + graph[y + 1][x + 1]);

        if (y > 0 && x < m - 2)
            result = Math.max(result, graph[y][x] + graph[y][x + 1] + graph[y][x + 2] + graph[y - 1][x + 1]);
    }

    public static class Prim {
        public static ArrayList<Node> list = new ArrayList<Node>();
        public static ArrayList<Node>[] nodeList;                   // 연결리스트
        public static int costs = 0;
        public static boolean[] visited;

        public static void main(String args[]){
            // 그래프 설정 ( 정점, 간선 , 비용)
            list.add(new Node(1, 2, 5));
            list.add(new Node(2, 3, 1));
            list.add(new Node(3, 6, 9));
            list.add(new Node(1, 4, 3));
            list.add(new Node(1, 3, 8));
            list.add(new Node(1, 5, 10));
            list.add(new Node(4, 5, 4));
            list.add(new Node(5, 6, 12));
            list.add(new Node(4, 6, 2));
            list.add(new Node(3, 4, 4));

            visited = new boolean[6+1];          // 1로 인덱스 맞추기 위함
            Arrays.fill(visited, false);         // 방문여부 초기화

            nodeList = new ArrayList[6+1];
            for(int i=1;i<7;i++){     // 연결리스트 초기화
                nodeList[i] = new ArrayList<Node>();
            }

            // 연결리스트에 간선과 비용 설정
            for(int i=0;i<list.size();i++){
                int start = list.get(i).start;
                int end = list.get(i).end;
                int cost = list.get(i).cost;
                nodeList[start].add(new Node(start, end, cost));
                nodeList[end].add(new Node(end, start, cost));
            }

            mst_prim(1);        // 시작 정점 1
            System.out.println(costs);
        }

        public static void mst_prim(int p){
            Queue<Integer> queue = new LinkedList<>();
            PriorityQueue<Node> pq = new PriorityQueue<>();
            queue.offer(p);

            while(!queue.isEmpty()){
                int n = queue.poll();
                System.out.println(n);
                visited[n] = true;

                for (Node node : nodeList[n]) {
                    if(!visited[node.end]) pq.offer(node);
                }

                while(!pq.isEmpty()){
                    Node e = pq.poll();

                    if(!visited[e.end]){
                        queue.offer(e.end);
                        visited[e.end] = true;
                        costs += e.cost;
                        break;
                    }

                }

            }
        }
    }
}
