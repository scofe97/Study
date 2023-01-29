package 알고리즘.문제코드.백준.단계810_정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_S5_11652 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Map<Long, Integer> map = new HashMap<>();
        Set<Map.Entry<Long, Integer>> set = map.entrySet();

        for (int i = 0; i < N; i++) {
            Long temp = Long.parseLong(br.readLine());
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }

        set.stream()
                .sorted(new Comparator<Map.Entry<Long, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Long, Integer> o1, Map.Entry<Long, Integer> o2) {
                        if (o2.getValue()
                                .equals(o1.getValue())) return Long.compare(o1.getKey(), o2.getKey());
                        return o2.getValue() - o1.getValue();
                    }
                })
                .limit(1)
                .forEach(i -> System.out.println(i.getKey()));


    }
}
