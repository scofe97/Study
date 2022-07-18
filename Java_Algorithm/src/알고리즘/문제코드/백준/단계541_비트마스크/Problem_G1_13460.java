package 알고리즘.문제코드.백준.단계541_비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G1_13460 {

    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static char[][] board;
    static boolean[][][][] checked;

    static Point init;
    static int y, x;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        board = new char[y][x];
        checked = new boolean[y][x][y][x];
        init = new Point();
        result = 0;

        for (int i = 0; i < y; i++) {
            String temp = br.readLine();

            for (int j = 0; j < x; j++) {
                if (temp.charAt(j) == 'R') {
                    init.r_y = i;
                    init.r_x = j;

                } else if (temp.charAt(j) == 'B') {
                    init.b_y = i;
                    init.b_x = j;
                }

                board[i][j] = temp.charAt(j);
            }
        }

        init.step = 0;
        bfs(init);

        System.out.println(result);


    }

    static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point temp = queue.poll();
            checked[temp.r_y][temp.r_x][temp.b_y][temp.b_x] = true;
            if (temp.step >= 10) {
                result = -1;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                boolean r_flag = false;
                boolean b_flag = false;

                boolean r_goal = false;
                boolean b_goal = false;

                int r_move_y = temp.r_y;
                int r_move_x = temp.r_x;

                int b_move_y = temp.b_y;
                int b_move_x = temp.b_x;

                int step = temp.step + 1;

                while (true) {
                    if (r_flag && b_flag) {
                        if (r_goal && !b_goal) {
                            result = step;
                            return;
                        } else if (b_goal) {
                            break;
                        } else {
                            if(!checked[r_move_y][r_move_x][b_move_y][b_move_x]){
                                checked[r_move_y][r_move_x][b_move_y][b_move_x] = true;
                                Point step_point = new Point();
                                step_point.r_x = r_move_x;
                                step_point.r_y = r_move_y;
                                step_point.b_x = b_move_x;
                                step_point.b_y = b_move_y;
                                step_point.step = step;

                                queue.add(step_point);
                            }
                            break;
                        }
                    }

                    if (!r_flag && r_move_x + dir_x[i] >= 0 && r_move_x + dir_x[i] < x
                            && r_move_y + dir_y[i] >= 0 && r_move_y + dir_y[i] < y) {

                        if (board[r_move_y + dir_y[i]][r_move_x + dir_x[i]] == '#') {
                            r_flag = true;
                        } else if (r_move_y + dir_y[i] == b_move_y && r_move_x + dir_x[i] == b_move_x) {
                            if (b_flag) {
                                r_flag = true;
                            }
                        } else if (board[r_move_y + dir_y[i]][r_move_x + dir_x[i]] == 'O') {
                            r_move_y = -1;
                            r_move_x = -1;
                            r_flag = true;
                            r_goal = true;
                        } else {
                            r_move_y += dir_y[i];
                            r_move_x += dir_x[i];
                        }

                    }

                    if (!b_flag && b_move_x + dir_x[i] >= 0 && b_move_x + dir_x[i] < x
                            && b_move_y + dir_y[i] >= 0 && b_move_y + dir_y[i] < y) {

                        if (board[b_move_y + dir_y[i]][b_move_x + dir_x[i]] == '#') {
                            b_flag = true;
                        } else if (b_move_y + dir_y[i] == r_move_y && b_move_x + dir_x[i] == r_move_x) {
                            if (r_flag) {
                                b_flag = true;
                            }
                        } else if (board[b_move_y + dir_y[i]][b_move_x + dir_x[i]] == 'O') {
                            b_move_y = -1;
                            b_move_x = -1;
                            b_flag = true;
                            b_goal = true;
                        } else {
                            b_move_y += dir_y[i];
                            b_move_x += dir_x[i];
                        }

                    }
                }
            }
        }
        result = -1;
    }

    private static class Point {
        int r_y;
        int r_x;
        int b_y;
        int b_x;

        int step;
    }
}
