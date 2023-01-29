package 알고리즘.문제코드.백준.단계541_비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_G2_12100_solution {
    static int n;
    static int max = 0;

    // 방향 'dr' = 0(상), 1(우), 2(하), 3(좌)
    public static int[][] move(int dr, int[][] board) {

//		for(int i = 0; i < n; i++) {
//			for(int j = 0; j < n; j++) {
//				System.out.print(board[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();

        if(dr == 0) { // 위로 이동
            for(int i = 0; i < n; i++) {
                int index = 0;
                int block = 0;
                for(int j = 0; j < n; j++) {
                    if(board[j][i] != 0) {
                        if(block == board[j][i]) {
                            board[index - 1][i] = block * 2;
                            block = 0;
                            board[j][i] = 0;
                        }
                        else {
                            block = board[j][i];
                            board[j][i] = 0;
                            board[index][i] = block;
                            index++;
                        }
                    }
                }
            }
        }
        else if(dr == 1) { // 오른쪽 이동
            for(int i = 0; i < n; i++) {
                int index = n - 1;
                int block = 0;
                for(int j = n - 1; j >= 0; j--) {
                    if(board[i][j] != 0) {
                        if(block == board[i][j]) {
                            board[i][index + 1] = block * 2;
                            block = 0;
                            board[i][j] = 0;
                        }
                        else {
                            block = board[i][j];
                            board[i][j] = 0;
                            board[i][index] = block;
                            index--;
                        }
                    }
                }
            }
        }
        else if(dr == 2) { // 아래로 이동
            for(int i = 0; i < n; i++) {
                int index = n - 1;
                int block = 0;
                for(int j = n - 1; j >= 0; j--) {
                    if(board[j][i] != 0) {
                        if(block == board[j][i]) {
                            board[index + 1][i] = block * 2;
                            block = 0;
                            board[j][i] = 0;
                        }
                        else {
                            block = board[j][i];
                            board[j][i] = 0;
                            board[index][i] = block;
                            index--;
                        }
                    }
                }
            }
        }
        else {	// 왼쪽 이동
            for(int i = 0; i < n; i++) {
                int index = 0;
                int block = 0;
                for(int j = 0; j < n; j++) {
                    if(board[i][j] != 0) {
                        if(block == board[i][j]) {
                            board[i][index - 1] = block * 2;
                            block = 0;
                            board[i][j] = 0;
                        }
                        else {
                            block = board[i][j];
                            board[i][j] = 0;
                            board[i][index] = block;
                            index++;
                        }
                    }
                }
            }
        }
        return board;
    }

    public static void dfs(int[][]board, int depth) {
        if(depth == 5) {
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    max = Math.max(max, board[i][j]);
            return;
        }

        int copy[][] = new int[n][n];
        for(int i = 0; i < n; i++)
            copy[i] = board[i].clone();

        for(int i = 0; i < 4; i++) {
            dfs(move(i, board), depth + 1);

            for(int j = 0; j < n; j++)
                board[j] = copy[j].clone();
        }
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][n];

        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);

        System.out.println(max);
        br.close();
    }
}


