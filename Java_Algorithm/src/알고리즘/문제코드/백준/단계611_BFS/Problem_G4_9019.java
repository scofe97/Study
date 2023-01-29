package 알고리즘.문제코드.백준.단계611_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G4_9019 {

    static int start;
    static int end;
    static String op;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            op = "";
            visited = new boolean[10000];

            bfs();
            System.out.println(op);
        }
    }

    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(start, ""));

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (temp.p == end) {
                op = temp.step;
                return;
            }

            int temp_d = (temp.p * 2) % 10000;
            if (!visited[temp_d]) {
                visited[temp_d] = true;
                queue.add(new Point(temp_d, temp.step + "D"));
            }

            int temp_S = temp.p == 0 ? 9999 : temp.p - 1;
            if(!visited[temp_S]) {
                visited[temp_S] = true;
                queue.add(new Point(temp_S, temp.step + "S"));
            }


            int temp_l = (temp.p % 1000) * 10 + (temp.p / 1000);
            if (!visited[temp_l]) {
                visited[temp_l] = true;
                queue.add(new Point(temp_l, temp.step + "L"));
            }

            int temp_r = (temp.p % 10) * 1000 + (temp.p / 10);
            if (!visited[temp_r]) {
                visited[temp_r] = true;
                queue.add(new Point(temp_r, temp.step + "R"));
            }
        }
    }

    static class Point {
        int p;
        String step;

        Point(int p, String step) {
            this.p = p;
            this.step = step;
        }
    }
}

// RSLSSSD SSSRDSL