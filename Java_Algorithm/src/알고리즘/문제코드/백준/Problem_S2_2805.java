package 알고리즘.문제코드.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_S2_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        int goal = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < cnt; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.sort(Comparator.naturalOrder());

        int start = 0;
        int end = list.get(list.size() - 1);
        long sum;
        long result = 0;
        do {
            int point = (start + end) / 2;
            sum = list.stream().mapToLong(i -> i).filter(i -> i > point).map(i -> i - point).sum();
            if (sum > goal) {
                start = point + 1;
                result = Math.max(point, result);
            } else if (sum < goal) {
                end = point - 1;
            } else {
                result = point;
                break;
            }

        } while (start <= end);

        System.out.println(result);
    }
}
