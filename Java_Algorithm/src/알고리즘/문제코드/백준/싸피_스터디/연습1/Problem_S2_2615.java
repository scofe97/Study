package 알고리즘.문제코드.백준.싸피_스터디.연습1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S2_2615 {

    static int X;
    static int Y;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[19][19];

        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (arr[j][i] != 0 && check(j, i, arr[j][i])) {
                    System.out.println(arr[j][i]);
                    System.out.println((X+1) + " " + (Y+1));
                    return;
                }
            }
        }

        System.out.println(0);


    }

    public static boolean check(int x, int y, int type) {

        // 가로
        boolean left = true;
        boolean right = true;
        int count = 0;
        for (int i = 1; i < 19; i++) {
            if(!left && !right){
                break;
            }

            if(left){
                if(y - i < 0 || arr[x][y - i] != type){
                    left = false;
                }
                count++;
            }

            if(right){
                if(y + i >= 19 || arr[x][y + i] != type){
                    right = false;
                }
                count++;
            }
        }
        if(count == 6){
            X = x;
            Y = y;
            return true;
        }


        // 세로
        left = true;
        right = true;
        count = 0;
        for (int i = 1; i < 19; i++) {
            if(!left && !right){
                break;
            }

            if(left){
                if(x - i < 0 || arr[x - i][y] != type){
                    left = false;
                }
                count++;
            }

            if(right){
                if(x + i >= 19 || arr[x + i][y] != type){
                    right = false;
                }
                count++;
            }
        }
        if(count == 6){
            X = x;
            Y = y;
            return true;
        }


        // 대각선 ↘
        left = true;
        right = true;
        count = 0;
        for (int i = 1; i < 19; i++) {
            if(!left && !right){
                break;
            }

            if(left){
                if(x + i >= 19 || y + i >= 19 || arr[x + i][y + i] != type){
                    left = false;
                }
                count++;
            }

            if(right){
                if(x - i < 0 || y - i < 0 || arr[x - i][y - i] != type){
                    right = false;
                }
                count++;
            }
        }
        if(count == 6){
            X = x;
            Y = y;
            return true;
        }


        // 대각선 ↘
        left = true;
        right = true;
        count = 0;
        for (int i = 1; i < 19; i++) {
            if(!left && !right){
                break;
            }

            if(left){
                if(x + i >= 19 || y - i < 0 || arr[x + i][y - i] != type){
                    left = false;
                }
                count++;
            }

            if(right){
                if(x - i < 0 || y + i >= 19 || arr[x - i][y + i] != type){
                    right = false;
                }
                count++;
            }
        }
        if(count == 6){
            X = x;
            Y = y;
            return true;
        }


        return false;
    }
}
