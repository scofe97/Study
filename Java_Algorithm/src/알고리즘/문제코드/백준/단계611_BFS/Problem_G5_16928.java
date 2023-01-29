package 알고리즘.문제코드.백준.단계611_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G5_16928 {

    static boolean[] visited;
    static ArrayList<Point> n_list;
    static ArrayList<Point> m_list;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        n_list = new ArrayList<>();
        m_list = new ArrayList<>();
        visited = new boolean[101];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            n_list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            m_list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        bfs(new Point(0, 0));
        System.out.println(result);
    }

    static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            for (int i = 1; i <= 6; i++) {
                int move_point = temp.point + i;

                if (move_point <= 100 && !visited[move_point]) {
                    visited[move_point] = true;

                    if (move_point == 100) {
                        result = Math.min(temp.step, result);
                    }

                    for (Point point : n_list) {
                        if (move_point == point.u) {
                            move_point = point.v;
                            visited[move_point] = true;
                        }
                    }


                    for (Point point : m_list) {
                        if (move_point == point.u) {
                            move_point = point.v;
                            visited[move_point] = true;
                        }
                    }


                    queue.add(new Point(0, 0, move_point, temp.step + 1));
                }
            }
        }
    }

    static class Point {
        int u;
        int v;
        int point;
        int step;

        public Point(int u, int v) {
            this.u = u;
            this.v = v;
            this.point = 1;
            this.step = 1;
        }

        public Point(int u, int v, int point, int step) {
            this.u = u;
            this.v = v;
            this.point = point;
            this.step = step;
        }
    }
}
