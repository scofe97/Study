package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_D3_1228 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            StringBuilder sb = new StringBuilder();
            int size = Integer.parseInt(br.readLine());
            List<Integer> list = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < size; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int op_size = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < op_size; i++) {
                String op = st.nextToken();
                int idx = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());

                for (int j = 0; j < cnt; j++) {
                    list.add(idx + j, Integer.parseInt(st.nextToken()));
                }

            }
            sb.append("#").append(tc).append(" ");
            list.stream().limit(10).forEach(i -> sb.append(i).append(" "));
            System.out.println(sb.toString().trim());

        }
    }
}
