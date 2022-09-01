package 알고리즘.문제코드.SSAFY.시험_A형대비.SWEA_코드배틀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 1시간 12분
public class Problem_No1_낚시터_자리잡기 {

    static int size;
    static boolean[] visited;
    static boolean[] visited_fig;
    static Point[] point;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            size = Integer.parseInt(br.readLine());
            visited = new boolean[size];
            visited_fig = new boolean[3];
            point = new Point[3];
            result = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken()) - 1;
                int cnt = Integer.parseInt(st.nextToken());
                point[i] = new Point(idx, cnt);
            }

            dfs(0, 0);
            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(result)
                    .append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int step, int sumMove) {
        if (step == 3) {
            result = Math.min(result, sumMove);
            return;
        }
        for (int i = 0; i < 3; i++) {
            if(!visited_fig[i]) {
                visited_fig[i] = true;
                dfs(0, sumMove, i, step);
                visited_fig[i] = false;
            }
        }
    }

    static void dfs(int step, int sumMove, int cur, int dStep) {
        if (step == point[cur].cnt) {
            dfs(dStep + 1, sumMove);
            return;
        }
        for (int i = 0; i < size; i++) {
            if (point[cur].cnt - (step + 1) > 0) {
                if (point[cur].idx + i < size && !visited[point[cur].idx + i]) {
                    visited[point[cur].idx + i] = true;
                    dfs(step + 1, sumMove + i+1, cur, dStep);
                    visited[point[cur].idx + i] = false;
                    break;
                }

                if (point[cur].idx - i >= 0 && !visited[point[cur].idx - i]) {
                    visited[point[cur].idx - i] = true;
                    dfs(step + 1, sumMove + i+1, cur, dStep);
                    visited[point[cur].idx - i] = false;
                    break;
                }

            } else {
                boolean flagR = false;
                boolean flagL = false;
                if (point[cur].idx + i < size && !visited[point[cur].idx + i]) flagR = true;
                if (point[cur].idx - i >= 0 && !visited[point[cur].idx - i]) flagL = true;

                if(flagL || flagR){
                    if(flagL) {
                        visited[point[cur].idx - i] = true;
                        dfs(step + 1, sumMove + i+1, cur, dStep);
                        visited[point[cur].idx - i] = false;
                    }

                    if(flagR){
                        visited[point[cur].idx + i] = true;
                        dfs(step + 1, sumMove + i+1, cur, dStep);
                        visited[point[cur].idx + i] = false;
                    }
                    break;
                }
            }
        }
    }

    static class Point {
        int idx;
        int cnt;

        public Point(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }
}
