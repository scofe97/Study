package 알고리즘.문제코드.SSAFY.시험_A형대비.삼성A형_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CodeRotate {

    static int[][] graph;
    public static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int idx = 1;
        graph = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                graph[i][j] = idx++;
            }
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
        System.out.println();

        int d = 0;
        int startX = 4;
        int startY = 0;
        int curR = 4;
        int curC = 0;
        int temp= graph[curR][curC];
        while (true) { // 배열 회전
            int nx = curR + dx[d];
            int ny = curC + dy[d];

            // 회전 범위를 벗어나면 방향 변환
            if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                d = ++d % 4;

                nx = curR + dx[d];
                ny = curC + dy[d];
            }

            if (curR == startX && curC == startY) {
                curR = nx;
                curC = ny;
                continue;
            }

            // 최초 지점으로 복귀시 종료
            if (nx == startX && ny == startY) {
                graph[curR][curC] = 0;
                break;
            }

            // 다음 번 칸의 값을 가져와 저장
            graph[curR][curC] = graph[nx][ny];

            // 다음 번 칸으로 이동
            curR = nx;
            curC = ny;
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
    }
}
