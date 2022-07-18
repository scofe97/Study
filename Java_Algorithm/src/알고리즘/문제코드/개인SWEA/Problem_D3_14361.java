package 알고리즘.문제코드.개인SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Problem_D3_14361 {

    static boolean[] visited;
    static String num;
    static boolean result = false;

    static ArrayList<Integer> test = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            num = br.readLine();
            StringBuffer sb = new StringBuffer();
            visited = new boolean[num.length()];
            dfs(0, sb);

            if (result) {
                System.out.println("#" + tc + " possible");
            } else {
                System.out.println("#" + tc + " impossible");
            }

            result = false;
        }
    }

    static void dfs(int step, StringBuffer sb) {
        if (step == num.length()) {
            int temp_num = Integer.parseInt(sb.toString());
            int result_num = Integer.parseInt(num);

            test.add(temp_num);
            if (temp_num / result_num >= 2 && temp_num % result_num == 0) {
                result = true;
            }

            return;
        }

        for (int s = 0; s < num.length(); s++) {
            if (!visited[s]) {
                if(s == 0 && num.charAt(s) == '0'){
                    continue;
                }
                visited[s] = true;
                sb.append(num.charAt(s));
                dfs(step + 1, sb);
                sb.deleteCharAt(sb.length()-1);
                visited[s] = false;
            }
        }

    }
}
