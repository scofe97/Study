package 알고리즘.문제코드.SSAFY.수업_221005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_G1_17143 {

    static int r;
    static int c;
    static int m;

    static int[][] map;
    static List<Shark> list;

    static int[] dir_x = {-1, 1, 0, 0};
    static int[] dir_y = {0, 0, 1, -1};

    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int shark_r = Integer.parseInt(st.nextToken());
            int shark_c = Integer.parseInt(st.nextToken());
            int shark_s = Integer.parseInt(st.nextToken());
            int shark_d = Integer.parseInt(st.nextToken());
            int shark_z = Integer.parseInt(st.nextToken());
            Shark temp = new Shark(shark_r - 1, shark_c - 1, shark_s, shark_d - 1, shark_z);
            map[shark_r - 1][shark_c - 1] = shark_z;
            list.add(temp);
        }

        Collections.sort(list);

        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (map[j][i] != 0) {
                    for (Shark shark : list) {
                        if (shark.r == j && shark.c == i) {
                            result += map[j][i];
                            shark.z = -1;
                            break;
                        }
                    }
                    map[j][i] = 0;
                    break;
                }
            }
            sharkMove();
        }

        System.out.println(result);

    }

    static void sharkMove() {
        for (Shark shark : list) {
            if (shark.z == -1 || shark.s == 0) continue;

            if(map[shark.r][shark.c] == shark.z) map[shark.r][shark.c] = 0;

            if(shark.d < 2){
                shark.s %= (r-1) *2;
            }else{
                shark.s %= (c-1) *2;
            }

            for (int i = 0; i < shark.s; i++) {
                int mr = shark.r + dir_x[shark.d];
                int mc = shark.c + dir_y[shark.d];

                if (mr < 0 || mr >= r || mc < 0 || mc >= c) {
                    i--;
                    shark.d = shark.d % 2 == 0 ? shark.d + 1 : shark.d - 1;
                    continue;
                }

                shark.r = mr;
                shark.c = mc;
            }

            if (map[shark.r][shark.c] > shark.z) {
                shark.z = -1;
            } else {
                map[shark.r][shark.c] = shark.z;
            }
        }
    }

    static class Shark implements Comparable<Shark> {
        int r;
        int c;
        int s; // 속력
        int d; // 방향
        int z; // 크기

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public int compareTo(Shark o) {
            return o.z - this.z;
        }
    }
}

/*
4 4 4
1 1 1 4 1
2 1 1 4 2
3 1 1 4 3
4 1 1 4 4

4 4 4
1 1 1 3 1
1 3 1 4 2
2 1 1 3 3
2 3 1 4 4

2 2 4
1 1 1 3 1
1 2 1 4 2
2 1 1 3 3
2 2 1 4 4

3 3 4
1 2 1 2 1
2 3 1 4 2
3 2 1 1 3
 */
