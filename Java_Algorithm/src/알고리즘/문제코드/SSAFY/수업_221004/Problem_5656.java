package 알고리즘.문제코드.SSAFY.수업_221004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_5656 {

    static int[] dir_x = {0, -1, 0, 1};
    static int[] dir_y = {-1, 0, 1, 0};

    static int n;
    static int w;
    static int h;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            result = Integer.MAX_VALUE;

            int[][] map = new int[h][w];

            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, map);
            System.out.println("#" + tc + " " + result);
        }
    }

    private static void dfs(int cnt, int[][] step_map){
        if(cnt == n){
            int block = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(step_map[i][j] != 0)  block++;
                }
            }
            result = Math.min(result, block);
            return;
        }

        boolean check = false;
        for (int i = 0; i < w; i++) {
            int[][] copy_map = new int[h][w];
            for (int j = 0; j < h; j++) {
                if(step_map[j][i] > 0) {
                    for (int k = 0; k < step_map.length; k++) {
                        System.arraycopy(step_map[k], 0, copy_map[k], 0, copy_map[k].length);
                    }
                    bfs(copy_map, new Point(j, i, copy_map[j][i]));
                    blockGravity(copy_map);
                    check = true;
                    break;
                }
            }


            if(check){
                check = false;
                dfs(cnt+1, copy_map);
            }else{
                dfs(cnt+1, step_map);
            }
        }
    }

    private static void bfs(int[][] step_map, Point start){

        Queue<Point> queue = new LinkedList<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            Point temp = queue.poll();

            for (int i = 0; i < temp.power; i++) {
                for (int j = 0; j < 4; j++) {
                    int mx = temp.x + dir_x[j] * i;
                    int my = temp.y + dir_y[j] * i;

                    if(mx < 0 || mx >= h || my < 0 || my >= w) continue;
                    if(step_map[mx][my] > 1 && i > 0) queue.offer(new Point(mx, my, step_map[mx][my]));
                    step_map[mx][my] = 0;
                }
            }

        }
    }

    private static void  blockGravity(int[][] step_map){
        for (int y = 0; y < w; y++) {
            Queue<Integer> bricks = new LinkedList<>();

            int x = h-1;
            while(x>=0) {
                if(step_map[x][y] > 0) {
                    bricks.offer(step_map[x][y]);
                    step_map[x][y] = 0;
                }
                x--;
            }

            x = h-1;
            while(!bricks.isEmpty()){
                step_map[x][y] = bricks.poll();
                x--;
            }
        }
    }


    private static class Point{
        int x;
        int y;
        int power;

        public Point(int x, int y, int power) {
            this.x = x;
            this.y = y;
            this.power = power;
        }
    }
}

/*
5
2 5 5
0 0 0 0 0
0 0 0 0 0
1 1 3 1 1
0 0 0 0 0
0 1 2 1 1

 */