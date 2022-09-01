package 알고리즘.문제코드.SSAFY.수업_220824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G5_10026 {
    static int n;
    static char[][] graph;
    static boolean[][] visited;
    static boolean[][] visited2;

    static int[] dir_y = {-1, 1, 0, 0};
    static int[] dir_x = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new char[n][n];
        visited = new boolean[n][n];
        visited2 = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        int result = 0;
        int result2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(!visited[i][j]) {
                    bfs(i, j , graph[i][j]);
                    result++;
                }
                if(!visited2[i][j]){
                    bfs2(i, j , graph[i][j]);
                    result2++;
                }
            }
        }

        System.out.println(result + " " + result2);


    }

    static void bfs(int x, int y, char c){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int[] point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int mx = point[0] + dir_x[i];
                int my = point[1] + dir_y[i];

                if(mx< 0 || my < 0 || mx >= n || my >= n) continue;

                if(!visited[mx][my] && graph[mx][my] == c) {
                    visited[mx][my] = true;
                    queue.offer(new int[]{mx,my});
                }

            }
        }
    }

    static void bfs2(int x, int y, char c){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        visited2[x][y] = true;

        while(!queue.isEmpty()){
            int[] point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int mx = point[0] + dir_x[i];
                int my = point[1] + dir_y[i];

                if(mx< 0 || my < 0 || mx >= n || my >= n) continue;

                if(c == 'R' || c == 'G'){
                    if(!visited2[mx][my] && (graph[mx][my] == 'R' || graph[mx][my] == 'G')) {
                        visited2[mx][my] = true;
                        queue.offer(new int[]{mx,my});
                    }
                }else{
                    if(!visited2[mx][my] && graph[mx][my] == c) {
                        visited2[mx][my] = true;
                        queue.offer(new int[]{mx,my});
                    }
                }
            }
        }
    }
}
