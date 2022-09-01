package 알고리즘.문제코드.SSAFY.수업_220825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G1_17472 {

    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};

    static int n;
    static int m;
    static int[][] graph;
    static List<List<Point>> lists;
    static int result;

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        lists = new ArrayList<>();
        result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int temp = Integer.parseInt(st.nextToken());
                graph[i][j] = temp;
            }
        }

        int num = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1) bfs(i, j, num++);
            }
        }

        parents = new int[num-2];
        for (int i = 0; i < num-2; i++) {
            parents[i]= i;
        }

        dfs(0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);


    }

    static void dfs(int step, int r) {
        if (step == lists.size()-1) {
            result = Math.min(result, r);
            return;
        }

        for (int i = 0; i < lists.size(); i++) {
            List<Point> temp = lists.get(i);
            for (int j = 0; j < temp.size(); j++) {
                Point p = temp.get(j);
                int mx = p.x;
                int my = p.y;
                int num = p.num;

                for (int k = 0; k < 4; k++) {
                    make(mx, my, k, num, step, r);
                }


            }
        }
    }

    static boolean cycle(int v1, int v2){
        int v1Root = find(v1);
        int v2Root = find(v2);

        if(v1Root == v2Root) return true;
        else return false;
    }

    static int find(int v){
        if(parents[v] == v) return v;
        else return find(parents[v]);
    }

    static void make(int x, int y, int dir, int num, int step, int result) {
        int mx = x;
        int my = y;
        int bridge = 0;

        while (true) {
            mx += dir_x[dir];
            my += dir_y[dir];


            if (mx < 0 || my < 0 || mx >= n || my >= m
                    || graph[mx][my] == num)
                return;

            if (graph[mx][my] != 0) {
                if(!cycle(graph[mx][my]-2, num-2) && bridge >= 2){
                    int v1Root = find(graph[mx][my]-2);
                    int v2Root = find(num-2);

                    if(v1Root < v2Root) parents[v2Root] =v1Root;
                    else parents[v1Root] =v2Root;

                    dfs(step + 1, result + bridge);

                    parents[v2Root] = v2Root;
                    parents[v1Root] = v1Root;
                }

                return;
            }

            bridge++;
        }
    }

    static void bfs(int x, int y, int num) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> list = new ArrayList<>();
        list.add(new Point(x, y, num));
        queue.offer(new Point(x, y, num));
        graph[x][y] = num;

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            for (int i = 0; i < 4; i++) {
                int mx = temp.x + dir_x[i];
                int my = temp.y + dir_y[i];

                if (mx < 0 || my < 0 || mx >= n || my >= m) continue;

                if (graph[mx][my] == 1) {
                    graph[mx][my] = num;
                    list.add(new Point(mx, my, num));
                    queue.offer(new Point(mx, my, temp.num));
                }
            }
        }

        lists.add(list);
    }

    static class Point {
        int x;
        int y;
        int num;

        public Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}
