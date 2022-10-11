package 알고리즘.문제코드.SSAFY.수업_221011;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Problem_4014 {

    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static int[][] map;
    static int N;
    static int X;
    static int ans;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            X = Integer.parseInt(input[1]);
            map = new int[N][N];
            ans = 0;

            for(int i=0; i<N; i++) {
                input = br.readLine().split(" ");
                for(int j=0; j<N; j++)
                    map[i][j] = Integer.parseInt(input[j]);
            }

            for(int i=0; i<N; i++)
                dfs(0, i, 0, 1, false);

            for(int i=0; i<N; i++)
                dfs(i, 0, 1, 1, false);

            System.out.println("#"+test_case+" "+ans);
        }
    }

    public static void dfs(int x, int y, int idx, int cnt, boolean flag) {
        int nx = x+dx[idx];
        int ny = y+dy[idx];

        if(nx==N || ny==N) {
            if(!flag || cnt >= X) ans++;
            return;
        }

        if(map[x][y] == map[nx][ny])
            dfs(nx, ny, idx, cnt+1, flag);

        else {
            if(Math.abs(map[x][y] - map[nx][ny])>=2) return;

            if(map[x][y]<map[nx][ny] && !flag && cnt>=X)
                dfs(nx, ny, idx, 1, false);

            else if(map[x][y]<map[nx][ny] && flag && cnt>=2*X)
                dfs(nx, ny, idx, 1, false);

            else if(map[x][y]>map[nx][ny] && flag && cnt>=X)
                dfs(nx, ny, idx, 1, true);

            else if(map[x][y]>map[nx][ny] && !flag)
                dfs(nx, ny, idx, 1, true);
        }
    }
}

/*
1
6 2
3 3 3 2 1 1
3 3 3 2 2 1
3 3 3 3 3 2
2 2 3 2 2 2
2 2 3 2 2 2
2 2 2 2 2 2
 */
