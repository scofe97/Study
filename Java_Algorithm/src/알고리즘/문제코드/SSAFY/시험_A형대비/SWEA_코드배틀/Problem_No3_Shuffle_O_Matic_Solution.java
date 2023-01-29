package 알고리즘.문제코드.SSAFY.시험_A형대비.SWEA_코드배틀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_No3_Shuffle_O_Matic_Solution {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T, N, halfN, result;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            result = 9;    // 셔플 최대 횟수는 5번이므로 그보다 높은 값으로 설정(9는 의미 없음)
            N = Integer.parseInt(br.readLine());
            halfN = N / 2;
            int[] card = new int[N];
            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i < N; i++) {
                card[i] = Integer.parseInt(st.nextToken());
            } // 입력 끝

            int[] left = new int[halfN];
            int[] right = new int[halfN];
            left = Arrays.copyOfRange(card, 0, halfN);    // 왼쪽 절반
            right = Arrays.copyOfRange(card, halfN, N);    // 오른쪽 절반

            shuffle(0, card, left, right);
            if (result != 9) sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(result)
                    .append("\n");
            else sb.append("#")
                    .append(tc)
                    .append(" ")
                    .append(-1)
                    .append("\n");

        }

        System.out.print(sb);

    }

    static void shuffle(int cnt, int[] card, int[] left, int[] right) {

        // cnt가 커져서 셔플 더 할 필요 없을 때
        if (cnt >= result) return;

        // 오름차순이나 내림차순으로 정렬이 돼있으면 result 갱신
        if (checkOrder(card) || checkReverseOrder(card)) {
            if (result > cnt) result = cnt;
            return;
        }
        // 셔플 5번 해도 안되면 접으셈
        if (cnt == 5) return;

        int x = 0;
        while (++x < N) {
            int idxCard = 0;
            int idxLeft = 0;
            int idxRight = 0;

            // x에 따라 왼쪽 카드를 먼저 놓을지 오른쪽 카드를 먼저 놓을지 결정됨
            if (x <= halfN) {

                // 왼쪽 카드를 먼저 놓을 것 (몇 개만큼 먼저 놓을 것인가?)
                for (int i = 0; i < halfN - x; i++) {
                    card[idxCard++] = left[idxLeft++];
                }

            // 오른쪽 카드를 먼저 놓을 것 (몇 개만큼 먼저 놓을 것인가?)
            } else {
                for (int i = 0; i < x - halfN; i++) {
                    card[idxCard++] = right[idxRight++];
                }
            }

            // 왼쪽카드와 오른쪽카드를 다 쓸 때까지 가능하다면 하나씩 놓음
            while (idxCard < N) {

                // 오른쪽 카드를 모두 소진하지 않았으면 하나 놓음
                if (idxRight < halfN) card[idxCard++] = right[idxRight++];

                // 왼쪽 카드를 모두 소진하지 않았으면 하나 놓음
                if (idxLeft < halfN) card[idxCard++] = left[idxLeft++];
            }

            // 지금까지 섞어놓은 카드와 왼쪽카드, 오른쪽 카드 정보를 들고 다음 셔플로 넘어감
            shuffle(cnt + 1, card, Arrays.copyOfRange(card, 0, halfN), Arrays.copyOfRange(card, halfN, N));

        }

    }

    static boolean checkOrder(int[] card) {

        for (int i = 1; i < N; i++) {

            // 정렬이 안돼있으면 탈출 후 false 반환
            if (card[i] < card[i - 1]) break;

            // 끝까지 break되지 않으면 정렬돼있다는 뜻이므로 true 반환
            if (i == N - 1) {
                return true;
            }
        } // 지금 카드가 오름차순이면 true

        return false;

    }

    static boolean checkReverseOrder(int[] card) {

        for (int i = 1; i < N; i++) {

            // 정렬이 안돼있으면 탈출 후 false 반환
            if (card[i] > card[i - 1]) break;

            // 끝까지 break되지 않으면 정렬돼있다는 뜻이므로 true 반환
            if (i == N - 1) {
                return true;
            }
        } // 지금 카드가 내림차순이면 true

        return false;

    }
}
