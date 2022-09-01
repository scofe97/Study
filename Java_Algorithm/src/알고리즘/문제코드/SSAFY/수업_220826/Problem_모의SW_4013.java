package 알고리즘.문제코드.SSAFY.수업_220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_모의SW_4013 {

    static int rotateCnt;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            rotateCnt = Integer.parseInt(br.readLine());
            result = 0;
            int[][] graph = new int[4][8];
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < rotateCnt; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken())-1;
                int dir = Integer.parseInt(st.nextToken());

                rotateStart(idx, dir, graph);
            }

            for (int i = 1; i < 4; i++) {
                if(graph[i][0] == 1) {
                    int pow = 2;
                    for (int j = 1; j < i; j++) {
                        pow *= 2;
                    }

                    result += pow;
                }
            }

            result += graph[0][0];

            System.out.println("#" + tc + " " + (result));


        }

    }

    //  N극이 0 으로, S극이 1
    static void rotateStart(int idx, int dir, int[][] graph){
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{idx, dir});

        boolean right = true;
        boolean left = true;
        int dirChange = dir;
        for (int i = 1; i < 4; i++) {
            dirChange = dirChange * -1;
            if(right && idx + i < 4 && graph[idx + i - 1][2] != graph[idx + i][6]){
                list.add(new int[]{idx+i, dirChange});
            }else{
                right = false;
            }

            if(left && idx - i >= 0 && graph[idx - i + 1][6] != graph[idx - i][2]){
                list.add(new int[]{idx-i, dirChange});
            }else{
                left = false;
            }
        }

        for (int[] arr : list) {
            rotate(arr[0], arr[1], graph);
        }
    }

    static void rotate(int idx, int dir, int[][] graph){
        if(dir == 1){
            int temp = graph[idx][7];

            for (int i = 7; i > 0; i--) {
                graph[idx][i] = graph[idx][i-1];
            }
            graph[idx][0] = temp;
        }else{
            int temp = graph[idx][0];

            for (int i = 0; i < 7; i++) {
                graph[idx][i] = graph[idx][i+1];
            }
            graph[idx][7] = temp;
        }
    }
}
