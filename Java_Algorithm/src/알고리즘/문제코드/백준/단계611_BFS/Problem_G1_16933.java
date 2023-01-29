package 알고리즘.문제코드.백준.단계611_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 22분
public class Problem_G1_16933 {

    static boolean [][][][] visited;
    static char[][] graph;
    static int n;
    static int m;
    static int count;
    static int result = -1;

    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        visited = new boolean[2][count+1][n][m];

        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        bfs();

        System.out.println(result);

    }

    static void bfs(){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0,0,0, 1, 0));
        visited[0][0][0][0] = true;

        while(!queue.isEmpty()){
            Point temp = queue.poll();
            int nNight = temp.night == 1 ? 0 : 1;

            if(temp.x == n-1 && temp.y == m-1){
                result = temp.step;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + dir_x[i];
                int ny = temp.y + dir_y[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                    continue;
                }

                if(graph[nx][ny] == '1'
                        && temp.power != count
                        && temp.night == 0
                        && !visited[1][temp.power + 1][nx][ny]){
                    visited[1][temp.power + 1][nx][ny] = true;
                    queue.offer(new Point(nx, ny, temp.power + 1, temp.step + 1, nNight));


                } else if (graph[nx][ny] == '0'
                        && !visited[temp.night][temp.power][nx][ny]) {
                    visited[temp.night][temp.power][nx][ny] = true;
                    queue.offer(new Point(nx, ny, temp.power, temp.step + 1, nNight));
                }
            }

            if(!visited[nNight][temp.power][temp.x][temp.y]){
                visited[nNight][temp.power][temp.x][temp.y] = true;
                queue.offer(new Point(temp.x, temp.y, temp.power, temp.step + 1, nNight));
            }
        }
    }

    static class Point{
        int x;
        int y;
        int power;
        int step;
        int night;

        Point(int x, int y, int power, int step, int night){
            this.x = x;
            this.y = y;
            this.power = power;
            this.step = step;
            this.night = night;
        }
    }
}
