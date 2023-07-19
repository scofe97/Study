package 알고리즘.문제코드.이론.Graph;

public class Dijkstra {
    private static final int INF = 99999;
    private static final int V = 5;

    void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        int prev[] = new int[V];

        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            visited[i] = false;
            prev[i] = -1;
        }
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            int mv = minDistance(dist, visited);
            visited[mv] = true;

            for (int j = 0; j < V; j++) {
                if (!visited[j]
                        && graph[mv][j] != 0
                        && dist[mv] != INF
                        && dist[mv] + graph[mv][j] < dist[j]){
                    dist[j] = dist[mv] + graph[mv][j];
                    prev[j] = mv;
                }
            }
        }

        printSolution(dist, prev);
    }

    void printSolution(int dist[], int prev[]) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
            printPath(prev, i);
            System.out.println();
        }
    }

    void printPath(int prev[], int i) {
        if (i < 0)
            return;
        printPath(prev, prev[i]);
        System.out.print(i + " ");
    }

    int minDistance(int[] dist, boolean[] visited) {
        int min = INF;
        int minIdx = -1;

        for (int i = 0; i < V; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                minIdx = i;
            }
        }

        return minIdx;
    }

    public static void main(String[] args) {
        Dijkstra t = new Dijkstra();
        int graph[][] = new int[][]{
                {0, 2, INF, INF, INF}, // Node 1
                {INF, 0, 1, 2, INF}, // Node 2
                {INF, INF, 0, INF, INF}, // Node 3
                {INF, INF, INF, 0, 1}, // Node 4
                {INF, INF, INF, INF, 0} // Node 5
        };
        t.dijkstra(graph, 0);
    }
}
