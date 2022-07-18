package 자바공부.자료구조;

import java.util.LinkedList;
import java.util.Queue;

public class EX_Queue {
    public static void main(String[] args) {

        // chapter 1. 초기화
        Queue<Integer> queue = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();


        // chapter 2. 추가, 삭제
        queue.add(1);
        queue.add(2);
        queue.offer(3);

        queue.poll(); // 1번째 값 반환, 비어있다면 null
        System.out.println(queue.remove()); // 1번째 값 제거
        queue.clear(); // 초기화

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        System.out.println(queue.peek());

        queue.size();

    }
}
