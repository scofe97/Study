package 알고리즘.문제코드.SSAFY.수업_220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_G4_4485_dij {

    static int[][] distance;
    static int[][][] prev;
    static boolean[][] visited;
    static int size;
    static int[][] graph;
    static int result;

    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};

    private static class Vertex implements Comparable<Vertex> {
        public int x;
        public int y;
        public int minDistance;  // 출발지에서 자신으로의 최소비용

        public Vertex(int x, int y, int minDistance) {
            this.x = x;
            this.y = y;
            this.minDistance = minDistance;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.minDistance - o.minDistance;
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = 1;
        while ((size = Integer.parseInt(br.readLine())) != 0) {
            graph = new int[size][size];
            distance = new int[size][size];
            prev = new int[size][size][2];
            visited = new boolean[size][size];
            result = Integer.MAX_VALUE;

            int startX = 0;
            int startY = 0;
            int endX = size-1;
            int endY = size-1;

            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine());
                Arrays.fill(distance[i], Integer.MAX_VALUE);
                for (int j = 0; j < size; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<Vertex> pq = new PriorityQueue<>();
            distance[startX][startY] = graph[startX][startY];
            pq.offer(new Vertex(startX, startY, distance[startX][startY]));

            while(!pq.isEmpty()){
                Vertex temp = pq.poll();

                if(temp.x == endX && temp.y == endY) break;

                for (int i = 0; i < 4; i++) {
                    int mx = temp.x + dir_x[i];
                    int my = temp.y + dir_y[i];

                    if(mx < 0 || my < 0 || mx >= size || my >= size) continue;

                    if(!visited[mx][my] && distance[mx][my] > distance[temp.x][temp.y] + graph[mx][my]){
                        distance[mx][my] = distance[temp.x][temp.y] + graph[mx][my];
                        prev[mx][my][0] = temp.x;
                        prev[mx][my][1] = temp.y;
                        pq.offer(new Vertex(mx, my, distance[mx][my]));
                    }
                }
            }

            System.out.println(distance[endX][endY]);
            dfs(endX, endY);
        }

    }
    static void dfs(int x, int y){
        System.out.println("x : " + x + ",   y : " + y);
        if(x == 0 && y == 0) return;
        dfs(prev[x][y][0], prev[x][y][1]);
    }
}
