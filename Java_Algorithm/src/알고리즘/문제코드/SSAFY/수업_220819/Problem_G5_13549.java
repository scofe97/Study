package 알고리즘.문제코드.SSAFY.수업_220819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G5_13549 {
    static StringBuilder sb;
    static StringTokenizer st;

    static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(bfs(start));
    }

    static int bfs(int start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        boolean[] visited = new boolean[100001];

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int point = temp[0];
            int step = temp[1];

            if(point == end) return step;

            if (point - 1 >= 0 && !visited[point - 1]) {
                visited[point - 1] = true;
                queue.offer(new int[]{point - 1, step + 1});
            }

            if (point + 1 < 100001 && !visited[point + 1]) {
                visited[point + 1] = true;
                queue.offer(new int[]{point + 1, step + 1});
            }

            if (point * 2 < 100001 && !visited[point * 2]) {
                visited[point + 1] = true;
                queue.offer(new int[]{point * 2, step + 1});
            }

        }
        return -1;
    }
}
