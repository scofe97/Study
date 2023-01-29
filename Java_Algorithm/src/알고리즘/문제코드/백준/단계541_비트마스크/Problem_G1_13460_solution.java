package 알고리즘.문제코드.백준.단계541_비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G1_13460_solution {

    static int n,m;
    static int[][] map;
    static boolean[][][][] checked;
    static int min = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0 ,0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        checked = new boolean[n][m][n][m];

        int rx =0, ry =0;
        int bx =0, by =0;

        for(int i=0; i<n; i++) {
            String[] line = br.readLine().split("");
            for(int j=0; j<m; j++) {
                // R : 47, B: 31, O : 44, #: 0, . : 11
                int num = line[j].charAt(0)-'0'+13;
                map[i][j] = num;
                if(num == 47) {
                    rx =i; ry=j;
                }else if(num == 31) {
                    bx= i; by=j;
                }
            }
        }

        bfs(rx,ry,bx,by,0);
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static void bfs(int rx, int ry, int bx, int by, int cnt) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {rx,ry,bx,by, cnt});
        checked[rx][ry][bx][by] =true;

        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int pCnt = pos[4];

            if(pCnt>=10) {
                return;
            }
            for(int i=0; i<4; i++){
                int nRx = pos[0];
                int nRy = pos[1];
                int nBx = pos[2];
                int nBy = pos[3];

                // 빨간 구슬 이동
                while(map[nRx+dx[i]][nRy+dy[i]] != 0) {
                    nRx += dx[i];
                    nRy += dy[i];
                    if(map[nRx][nRy] == 44) break;
                }

                // 파란 구슬 이동
                while(map[nBx+dx[i]][nBy+dy[i]] != 0) {
                    nBx += dx[i];
                    nBy += dy[i];
                    if(map[nBx][nBy] == 44) break;
                }

                // 파란 구슬이 구멍에 들어갔을 때
                if(map[nBx][nBy] == 44) continue;

                if(map[nRx][nRy] == 44) {
                    min = Math.min(min, pCnt+1);
                    return;
                }

                // 빨간 파랑 서로 만났을 때
                if(nRx == nBx && nRy == nBy && map[nRx][nRy] != 44) {
                    int red_move = Math.abs(nRx-pos[0]) + Math.abs(nRy-pos[1]);
                    int blue_move = Math.abs(nBx-pos[2]) + Math.abs(nBy-pos[3]);

                    // 파란 공이 더 빨리 도착한 경우
                    if(red_move > blue_move) {
                        nRx -= dx[i];
                        nRy -= dy[i];
                    }else { // 빨간 공이 더 빨리 도착한 경우
                        nBx -= dx[i];
                        nBy -= dy[i];
                    }
                }

                if(!checked[nRx][nRy][nBx][nBy]) {
                    checked[nRx][nRy][nBx][nBy] = true;
                    q.add(new int[] {nRx, nRy, nBx, nBy, pCnt+1});
                }
            }
        }

    }
}
