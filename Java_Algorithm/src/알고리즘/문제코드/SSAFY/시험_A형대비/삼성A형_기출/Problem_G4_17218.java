package 알고리즘.문제코드.SSAFY.시험_A형대비.삼성A형_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_G4_17218 {

    static int cnt;
    static int[][] better;
    static int result;

    static boolean[] select;
    static int[] lineUp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        cnt = Integer.parseInt(br.readLine());
        better = new int[cnt][9];
        result = 0;
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                better[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        select = new boolean[9];
        lineUp = new int[9];

        select[3] = true;
        lineUp[3] = 0;
        perm(1);

        System.out.println(result);
    }

    // 순열
    public static void perm(int num) {
        if (num == 9) {
            dfs(0, 0, 0, lineUp);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (select[i]) {
                continue;
            }

            select[i] = true;
            lineUp[i] = num;
            perm(num + 1);
            select[i] = false;
        }
    }


    static void dfs(int step, int seq, int point, int[] lineUp) {
        if (step == cnt) {
            result = Math.max(result, point);
            return;
        }


        int out = 0;
        int stepPoint = 0;
        int[] base = {0, 0, 0};

        while (out != 3) {
            if (better[step][lineUp[seq]] == 0) out++;

            else if (better[step][lineUp[seq]] == 1) {
                for (int i = 2; i >= 0; i--) {
                    if (base[i] == 1 && i + 1 >= 3) {
                        base[i] = 0;
                        stepPoint++;
                    } else if (base[i] == 1) {
                        base[i + 1] = 1;
                        base[i] = 0;
                    }
                }

                base[0] = 1;
            } else if (better[step][lineUp[seq]] == 2) {
                for (int i = 2; i >= 0; i--) {
                    if (base[i] == 1 && i + 2 >= 3) {
                        base[i] = 0;
                        stepPoint++;
                    } else if (base[i] == 1) {
                        base[i + 2] = 1;
                        base[i] = 0;
                    }
                }

                base[1] = 1;
            } else if (better[step][lineUp[seq]] == 3) {
                for (int i = 2; i >= 0; i--) {
                    if (base[i] == 1) {
                        stepPoint++;
                        base[i] = 0;
                    }
                }

                base[2] = 1;
            } else if (better[step][lineUp[seq]] == 4) {
                for (int i = 2; i >= 0; i--) {
                    if (base[i] == 1) {
                        stepPoint++;
                        base[i] = 0;
                    }
                }

                stepPoint++;
            }

            if (++seq == 9) seq = 0;

        }

        dfs(step + 1, seq, point + stepPoint, lineUp);
    }
}
