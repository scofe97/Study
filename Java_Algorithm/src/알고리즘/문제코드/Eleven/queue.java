package 알고리즘.문제코드.Eleven;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class queue {
    public int[] solution(int[] progresses, int[] speeds) {

        int count = 0;
        int after = 0;
        int[] answer = {};

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            int rest = 100 - progresses[i];
            queue.offer((int)Math.ceil(rest / speeds[i]));
        }

        while (!queue.isEmpty()) {
            count = 0;
            after += queue.peek();

            for (int day : queue) {
                if (day - after <= 0) {
                    queue.poll();
                    count++;
                }
            }

            result.add(count);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
