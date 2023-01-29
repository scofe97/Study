package 알고리즘.문제코드.백준.싸피_스터디.연습2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G3_16236_solution {

    static boolean[][] eat_visited;
    static int[][] graph;
    static int size;

    static int[] dir_x = {-1, 0, 0, 1};
    static int[] dir_y = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = Integer.parseInt(br.readLine());
        graph = new int[size][size];
        eat_visited = new boolean[size][size];

        Shark start = null;

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                int temp = Integer.parseInt(st.nextToken());

                if(temp == 9){
                    start = new Shark(i, j , 2, 0);
                    graph[i][j] = 0;
                }else{
                    graph[i][j] = temp;
                }
            }
        }

        System.out.println(bfs(start));

    }

    static int bfs(Shark start){
        if(start == null){
            return 0;
        }

        Queue<Shark> queue = new LinkedList<>();
        boolean[][] visited = new boolean[size][size];
        queue.offer(start);

        eat_visited[start.x][start.y] = true;
        visited[start.x][start.y] = true;

        int eat_cnt = 0;
        int result = 0;

        while(!queue.isEmpty()){
            Shark temp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = temp.x + dir_x[i];
                int ny = temp.y + dir_y[i];

                if(nx >= size || nx < 0 || ny >= size || ny < 0){
                    continue;
                }

                if(graph[nx][ny] <= temp.size&& !visited[nx][ny]){
                    if(!eat_visited[nx][ny] && graph[nx][ny] < temp.size && graph[nx][ny] > 0){
                        queue.clear();
                        eat_visited[nx][ny] = true;
                        visited = new boolean[size][size];
                        visited[nx][ny] = true;

                        eat_cnt++;
                        result += temp.step + 1;

                        if(eat_cnt == temp.size){
                            eat_cnt = 0;
                            queue.offer(new Shark(nx,ny, temp.size + 1, 0));
                        }else{
                            queue.offer(new Shark(nx,ny, temp.size, 0));
                        }
                        break;

                    }else{
                        visited[nx][ny] = true;
                        queue.offer(new Shark(nx, ny, temp.size, temp.step + 1));
                    }
                }
            }

        }

        return result;


    }

    static class Shark{
        int x;
        int y;
        int size;
        int step;

        Shark(int x, int y, int size, int step){
            this.x = x;
            this.y = y;
            this.size = size;
            this.step = step;
        }
    }
}
