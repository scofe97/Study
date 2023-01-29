package 알고리즘.문제코드.백준.단계541_비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Problem_G2_12100 {

    static int n;
    static int[][] board;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
        System.out.println(result);
    }

    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(board.clone(), 0));

        while (!queue.isEmpty()) {
            Point step_point = queue.poll();
            int step = step_point.step;

            if (step == 5) {
                return;
            }

            for (int i = 0; i < 4; i++) {
                int[][] step_board = new int[n][n];
                for(int q = 0; q < step_point.board.length; q++){ // 반복문 + ArrayCopy
                    System.arraycopy(step_point.board[q], 0, step_board[q], 0, step_board[q].length);
                }

                boolean sum = false;
                switch (i) {
                    // 1. 좌측으로 이동
                    case 0:
                        for (int l = 0; l < n; l++) {
                            Stack<Integer> temp = new Stack<>();
                            for (int m = 0; m < n; m++) {
                                if (sum && step_board[l][m] != 0 && !temp.isEmpty() && temp.peek() == step_board[l][m]) {
                                    temp.push(temp.pop() + step_board[l][m]);
                                    sum = false;

                                } else if (step_board[l][m] != 0) {
                                    temp.push(step_board[l][m]);
                                    sum = true;
                                }
                            }

                            Stack<Integer> temp_reverse = new Stack<>();
                            while(!temp.isEmpty()){
                                temp_reverse.push(temp.pop());
                            }


                            for (int m = 0; m < n; m++) {
                                if (!temp_reverse.isEmpty()) {
                                    int v = temp_reverse.pop();
                                    result = Math.max(result, v);
                                    step_board[l][m] = v;
                                } else {
                                    step_board[l][m] = 0;
                                }
                            }
                        }
                        queue.add(new Point(step_board, step + 1));
                        break;

                    // 2. 아래측으로 이동
                    case 1:
                        for (int l = 0; l < n; l++) {
                            Stack<Integer> temp = new Stack<>();
                            for (int m = n - 1; m >= 0; m--) {
                                if (sum && step_board[m][l] != 0 && !temp.isEmpty() && temp.peek() == step_board[m][l]) {
                                    temp.push(temp.pop() + step_board[m][l]);
                                    sum = false;

                                } else if (step_board[m][l] != 0) {
                                    temp.push(step_board[m][l]);
                                    sum = true;
                                }
                            }

                            Stack<Integer> temp_reverse = new Stack<>();
                            while(!temp.isEmpty()){
                                temp_reverse.push(temp.pop());
                            }

                            for (int m = 0; m < n; m++) {
                                if (!temp_reverse.isEmpty()) {
                                    int v = temp_reverse.pop();
                                    result = Math.max(result, v);
                                    step_board[m][l] = v;
                                } else {
                                    step_board[m][l] = 0;
                                }
                            }
                        }
                        queue.add(new Point(step_board, step + 1));
                        break;

                    // 3. 우측으로 이동
                    case 2:
                        for (int l = 0; l < n; l++) {
                            Stack<Integer> temp = new Stack<>();
                            for (int m = n - 1; m >= 0; m--) {
                                if (sum && step_board[l][m] != 0 && !temp.isEmpty() && temp.peek() == step_board[l][m]) {
                                    temp.push(temp.pop() + step_board[l][m]);
                                    sum = false;

                                } else if (step_board[l][m] != 0) {
                                    temp.push(step_board[l][m]);
                                    sum = true;
                                }
                            }

                            Stack<Integer> temp_reverse = new Stack<>();
                            while(!temp.isEmpty()){
                                temp_reverse.push(temp.pop());
                            }

                            for (int m = n - 1; m >= 0; m--) {
                                if (!temp_reverse.isEmpty()) {
                                    int v = temp_reverse.pop();
                                    result = Math.max(result, v);
                                    step_board[l][m] = v;
                                } else {
                                    step_board[l][m] = 0;
                                }
                            }
                        }
                        queue.add(new Point(step_board, step + 1));
                        break;

                    // 4. 위측으로 이동
                    case 3:
                        for (int l = 0; l < n; l++) {
                            Stack<Integer> temp = new Stack<>();
                            for (int m = 0; m < n; m++) {
                                if (sum && step_board[m][l] != 0 && !temp.isEmpty() && temp.peek() == step_board[m][l]) {
                                    temp.push(temp.pop() + step_board[m][l]);
                                    sum = false;

                                } else if (step_board[m][l] != 0) {
                                    temp.push(step_board[m][l]);
                                    sum = true;
                                }
                            }

                            Stack<Integer> temp_reverse = new Stack<>();
                            while(!temp.isEmpty()){
                                temp_reverse.push(temp.pop());
                            }

                            for (int m = 0; m < n; m++) {
                                if (!temp_reverse.isEmpty()) {
                                    int v = temp_reverse.pop();
                                    result = Math.max(result, v);
                                    step_board[m][l] = v;
                                } else {
                                    step_board[m][l] = 0;
                                }
                            }
                        }
                        queue.add(new Point(step_board, step + 1));
                        break;

                }
            }
        }
    }

    private static class Point {
        int[][] board;
        int step;

        public Point(int[][] board, int step) {
            this.board = board;
            this.step = step;
        }
    }

}


