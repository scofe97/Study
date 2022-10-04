package 알고리즘.문제코드.SSAFY.수업_221004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_G4_2239 {

    static List<Point> list;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        map = new int[9][9];
        list = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            String temp = br.readLine();

            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
                if (map[i][j] == 0) list.add(new Point(i, j));
            }
        }

        dfs(0);
    }

    static void dfs(int cnt) {
        if (cnt == list.size()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            System.exit(0);
        }

        Point temp = list.get(cnt);
        for (int i = 1; i <= 9; i++) {
            if (check(temp.x, temp.y, i)) {
                map[temp.x][temp.y] = i;
                dfs(cnt + 1);
                map[temp.x][temp.y] = 0;
            }
        }
    }

    static boolean check(int x, int y, int value) {
        // 가로, 세로
        for (int i = 0; i < 9; i++) {
            if (map[x][i] == value) return false;
            if (map[i][y] == value) return false;
        }
        // 3x3
        for (int i = x - (x % 3); i < x - (x % 3) + 3; i++) {
            for (int j = y - (y % 3); j < y - (y % 3) + 3; j++) {
                if (map[i][j] == value) return false;
            }
        }
        return true;
    }


    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
