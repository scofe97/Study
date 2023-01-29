package 알고리즘.문제코드.SSAFY.시험_A형대비.SWEA_코드배틀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// 시간 35분
public class Problem_No2_헌터 {

    static int N;
    static int[][] graph;
    static List<Point> monster;
    static List<Point> customer;
    static Point cur;

    static boolean[] checkMonster;
    static boolean[] checkCustomer;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            graph = new int[N][N];

            monster = new ArrayList<>();
            customer = new ArrayList<>();
            checkMonster = new boolean[N];
            checkCustomer = new boolean[N];

            cur = new Point(0, 0, 0);
            result = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int temp = Integer.parseInt(st.nextToken());
                    graph[i][j] = temp;

                    if (temp > 0) monster.add(new Point(i, j, Math.abs(temp)));
                    else if (temp < 0) customer.add(new Point(i, j, Math.abs(temp)));
                }
            }
            Collections.sort(monster);
            Collections.sort(customer);

            dfs(0, cur, 0);
            sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(result)
                    .append("\n");

        }
        System.out.println(sb);
    }

    static void dfs(int step, Point curPoint, int move) {
        if (step == monster.size() + customer.size()) {
            result = Math.min(result, move);
        }

        for (int i = 0; i < monster.size(); i++) {
            if (!checkMonster[i]) {
                checkMonster[i] = true;
                int temp = Math.abs(curPoint.x - monster.get(i).x) + Math.abs(curPoint.y - monster.get(i).y);
                dfs(step + 1, monster.get(i), move + temp);
                checkMonster[i] = false;
            }

            if (checkMonster[i] && !checkCustomer[i]) {
                checkCustomer[i] = true;
                int temp = Math.abs(curPoint.x - customer.get(i).x) + Math.abs(curPoint.y - customer.get(i).y);
                dfs(step + 1, customer.get(i), move + temp);
                checkCustomer[i] = false;
            }

        }
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int num;

        public Point(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Point o) {
            return o.num - this.num;
        }
    }
}
