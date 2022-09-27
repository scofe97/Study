package 알고리즘.문제코드.백준.싸피_스터디;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_P5_1385 {
    public static BufferedReader br;
    public static BufferedWriter bw;
    public static int S, T, sx, sy;
    public static int[][] map = new int[1500][1500];
    public static int[][] chk = new int[1500][1500];
    public static int[] dx = {-1, 0, 1, 1, 0, -1};
    public static int[] dy = {0, 1, 1, 0, -1, -1};

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void solve() throws IOException {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(sx, sy));
        chk[sx][sy] = -1;
        while (!q.isEmpty()) {
            Point cur = q.poll();
            //결과 출력
            if (map[cur.x][cur.y] == T) {
                int x = cur.x;
                int y = cur.y;
                Stack<Integer> st = new Stack<>();
                while (true) {
                    st.push(map[x][y]);
                    if (chk[x][y] == -1) break;
                    for (int d = 0; d < 6; d++) {
                        int X = x + dx[d];
                        int Y = y + dy[d];
                        if (map[X][Y] == chk[x][y]) {
                            x = X;
                            y = Y;
                            break;
                        }
                    }
                }
                while (!st.isEmpty()) {
                    bw.write(st.pop() + " ");
                }
                bw.write("\n");
                bw.flush();
                System.exit(0);
            }
            for (int d = 0; d < 6; d++) {
                int X = cur.x + dx[d];
                int Y = cur.y + dy[d];
                if (map[X][Y] == 0 || chk[X][Y] != 0) continue;
                chk[X][Y] = map[cur.x][cur.y];
                q.add(new Point(X, Y));
            }
        }
    }

    //벌집 맵을 그려줌
    public static void draw() {
        int x = 750;
        int y = 750;
        int N = 1;
        int K = 1; //확장값
        map[x][y] = 1;
        while (true) {
            for (int d = 0; d < 6; d++) {
                for (int k = 0; k < (d == 1 ? K - 1 : K); k++) {
                    int X = x + dx[d];
                    int Y = y + dy[d];
                    map[X][Y] = ++N;
                    //시작 숫자이면
                    if (N == S) {
                        sx = X;
                        sy = Y;
                    }
                    //최대 숫자이면
                    if (N == 1000000) return;
                    x = X;
                    y = Y;
                }
            }
            K++;
        }
    }

    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        input();
        draw();
        solve();
        bw.flush();
        bw.close();
        bw.close();
    }

}
