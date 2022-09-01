package 알고리즘.문제코드.SSAFY.수업_220822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem_G5_1759 {

    static int l;
    static int c;
    static char[] list;

    static int vowAll = 0;
    static int conAll = 0;
    static int vow = 0;
    static int con = 0;

    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        list = new char[c];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            char temp = st.nextToken()
                    .charAt(0);
            list[i] = temp;

            if (temp == 'a' || temp == 'e' || temp == 'i' || temp == 'o' || temp == 'u') vowAll++;
            else conAll++;
        }

        Arrays.sort(list);
        dfs(0, 0, vowAll, conAll);


    }

    static void dfs(int idx, int step, int remainVow, int remainCon) {
        if (step == l) {
            if(vow > 0 && con > 1) System.out.println(sb);
            return;
        }

        if (idx >= c || vow + remainVow < 1 || con + remainCon < 2) return;

        if (list[idx] == 'a' || list[idx] == 'e' || list[idx] == 'i' || list[idx] == 'o' || list[idx] == 'u') {
            vow++;
            sb.append(list[idx]);
            dfs(idx + 1, step + 1, remainVow - 1, remainCon);
            sb.deleteCharAt(sb.length() - 1);
            vow--;
            dfs(idx + 1, step, remainVow - 1, remainCon);
        } else {
            con++;
            sb.append(list[idx]);
            dfs(idx + 1, step + 1, remainVow, remainCon + -1);
            sb.deleteCharAt(sb.length() - 1);
            con--;
            dfs(idx + 1, step, remainVow, remainCon - 1);
        }
    }
}
