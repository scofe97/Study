package 알고리즘.문제코드.SSAFY.수업_221011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_G4_15961 {
    static int n, d, k, c;
    static int[] arr, visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        visited = new int[d + 1];

        System.out.println(slide());
    }

    static int slide() {
        int inSlide = 0, chance;

        for (int i = 0; i < k; i++) {
            if (visited[arr[i]] == 0) {
                inSlide++;
            }
            visited[arr[i]]++;
        }

        chance = inSlide;
        for (int i = 1; i < n; i++) {
            if (chance <= inSlide) {
                if (visited[c] == 0) {
                    chance = inSlide + 1;
                } else {
                    chance = inSlide;
                }
            }

            visited[arr[i - 1]]--;
            if (visited[arr[i - 1]] == 0) {
                inSlide--;
            }

            if (visited[arr[(i + k - 1) % n]] == 0) {
                inSlide++;
            }
            visited[arr[(i + k - 1) % n]]++;
        }
        return chance;
    }
}
