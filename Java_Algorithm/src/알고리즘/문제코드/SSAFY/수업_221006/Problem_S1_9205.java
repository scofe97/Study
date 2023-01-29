package 알고리즘.문제코드.SSAFY.수업_221006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S1_9205 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine()) + 2;
            int[][] map = new int[n][n];
            Point[] list = new Point[n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list[i] = new Point(x, y);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(i == j) continue;

                    int weight = Math.abs(list[i].x - list[j].x) + Math.abs(list[i].y - list[j].y);
                    if(weight <= 1000){
                        map[i][j] = weight;
                    }else{
                        map[i][j] = 1000000;
                    }
                }
            }

            for (int k = 1; k < n-1; k++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i == k || j == k) continue;
                        map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                    }
                }
            }

            int result = map[0][n-1];

            System.out.println(result == 1000000 ? "sad" : "happy");
        }
    }

    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
