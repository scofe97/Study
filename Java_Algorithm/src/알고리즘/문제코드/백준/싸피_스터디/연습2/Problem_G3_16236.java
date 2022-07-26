package 알고리즘.문제코드.백준.싸피_스터디.연습2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G3_16236 {

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

                if (temp == 9) {
                    start = new Shark(i, j, 0);
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = temp;
                }
            }
        }

        bfs(start);

    }

    static void bfs(Shark start) {
        if (start == null) {
            return;
        }

        Queue<Shark> queue = new LinkedList<>();
        queue.offer(start);
        int eat_cnt = 0;
        int shark_size = 2;
        int result = 0;

        while (true) {
            List<Shark> fish = new ArrayList<>();
            boolean[][] visited = new boolean[size][size];

            while (!queue.isEmpty()) {
                Shark temp = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = temp.x + dir_x[i];
                    int ny = temp.y + dir_y[i];

                    if (nx >= size || nx < 0 || ny >= size || ny < 0) {
                        continue;
                    }

                    if (!visited[nx][ny] && graph[nx][ny] <= shark_size) {
                        visited[nx][ny] = true;
                        queue.add(new Shark(nx, ny, temp.step + 1));
                        if (1 <= graph[nx][ny] && graph[nx][ny] < shark_size && !eat_visited[nx][ny]) {
                            fish.add(new Shark(nx, ny, temp.step + 1));
                        }
                    }
                }
            }

            if(fish.size() == 0){
                System.out.println(result);
                return;
            }

            Shark currentFish = fish.get(0);
            for (int i = 1; i < fish.size(); i++) {
                if(currentFish.step > fish.get(i).step){
                    currentFish = fish.get(i);
                } else if (currentFish.step == fish.get(i).step) {
                    if(currentFish.x > fish.get(i).x){
                        currentFish = fish.get(i);
                    } else if (currentFish.x == fish.get(i).x) {
                        if(currentFish.y > fish.get(i).y) {
                            currentFish = fish.get(i);
                        }
                    }
                }
            }

            result += currentFish.step;
            eat_cnt++;
            eat_visited[currentFish.x][currentFish.y] = true;

            if(eat_cnt == shark_size){
                shark_size++;
                eat_cnt = 0;
            }

            queue.offer(new Shark(currentFish.x, currentFish.y, 0));
        }
    }

    static class Shark {
        int x;
        int y;
        int step;

        Shark(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}