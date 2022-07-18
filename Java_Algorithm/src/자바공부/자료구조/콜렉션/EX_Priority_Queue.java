package 자바공부.자료구조.콜렉션;

import java.util.Collections;
import java.util.PriorityQueue;

public class EX_Priority_Queue {
    public static void main(String[] args) {

        // chapter 1. 초기화
        PriorityQueue<Integer> priorityQueue1 = new PriorityQueue<>(); // 우선순위 낮음
        PriorityQueue<Integer> priorityQueue2 = new PriorityQueue<>(Collections.reverseOrder()); // 우선순위 낮음


        // chapter 2. 추가, 제거
        priorityQueue1.add(1);
        priorityQueue1.add(1);
        priorityQueue1.offer(3);

        priorityQueue1.poll();
        priorityQueue1.remove();
        priorityQueue1.clear();


        // chapter 3. 출력
        System.out.println(priorityQueue1.peek());


    }

}
