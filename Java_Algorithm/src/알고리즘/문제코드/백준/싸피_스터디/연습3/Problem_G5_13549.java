package 알고리즘.문제코드.백준.싸피_스터디.연습3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G5_13549 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        System.out.println(bfs(start, end));
    }

    static int bfs(int start, int end) {
        boolean[] visited = new boolean[100001];
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(start, 0));
        int min_step = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            visited[temp.p] = true;

            if (temp.p == end) {
                min_step = Math.min(temp.step, min_step);
                continue;
            }

            if (temp.p * 2 <= 100000 && !visited[temp.p * 2]) {
                queue.offer(new Point(temp.p * 2, temp.step));
            }

            if (temp.p + 1 <= 100000 && !visited[temp.p + 1]) {
                queue.offer(new Point(temp.p + 1, temp.step + 1));
            }

            if (temp.p - 1 >= 0 && !visited[temp.p - 1]) {
                queue.offer(new Point(temp.p - 1, temp.step + 1));
            }


        }
        return min_step;
    }

    static class Point{
        int p;
        int step;

        Point(int p, int step) {
            this.p = p;
            this.step = step;
        }
    }

}
