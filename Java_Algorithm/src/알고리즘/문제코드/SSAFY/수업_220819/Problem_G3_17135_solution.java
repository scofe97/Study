package 알고리즘.문제코드.SSAFY.수업_220819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17135
public class Problem_G3_17135_solution {
    static int N, M, D;
    static int ans;
    static int[] archers;
    static int[][] map;
    static boolean[][] died;
    static Pos[] target;

    static class Pos {
        int r, c, d;

        public Pos(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        archers = new int[3];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(0, 0);
        System.out.println(ans);
    }

    static void comb(int idx, int cnt) {
        if (cnt == 3) {
            // 궁수 3명이 모두 위치하게됨
            int tmp = 0;
            died = new boolean[N][M];

            // 총 N번 공격을함
            for (int i = 0; i < N; i++) {
                // 궁수가 1칸씩 올라감
                tmp += attack(N - i);
            }

            ans = Math.max(ans, tmp);
            return;
        }

        // 궁수위치 선정
        if (idx == M) return;
        archers[cnt] = idx;
        comb(idx + 1, cnt + 1);
        comb(idx + 1, cnt);
    }

    static int attack(int r) {
        // 각자의 표적
        target = new Pos[3];

        int cnt = 0;

        // 각자가 화살을 쏜다
        for (int i = 0; i < 3; i++)
            bfs(i, r);

        //
        for (int i = 0; i < 3; i++) {
            if (target[i] == null || died[target[i].r][target[i].c]) continue;
            died[target[i].r][target[i].c] = true;
            cnt++;
        }
        return cnt;
    }

    static void bfs(int i, int r) {
        if (map[r - 1][archers[i]] == 1 && !died[r - 1][archers[i]]) {
            target[i] = new Pos(r - 1, archers[i], 0);
            return;
        }
        // 좌 상 우
        int[] dr = {0, -1, 0};
        int[] dc = {-1, 0, 1};
        boolean[][] visited = new boolean[N][M];

        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(r -1 , archers[i], 1));
        visited[r - 1][archers[i]] = true;

        while(!q.isEmpty()) {
            Pos cur = q.poll();

            // 사정거리에 접어들면 멈춘다.
            if (cur.d == D) break;


            for (int j = 0; j < 3; j++) {
                int nr = cur.r + dr[j];
                int nc = cur.c + dc[j];
                if (!isValid(nr, nc) || visited[nr][nc] || died[nr][nc]) continue;

                if (map[nr][nc] == 1) {
                    target[i] = new Pos(nr, nc, 0);
                    return;
                }

                q.offer(new Pos(nr, nc, cur.d + 1));
            }
        }
    }

    static boolean isValid(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= M)
            return false;
        return true;
    }

}
