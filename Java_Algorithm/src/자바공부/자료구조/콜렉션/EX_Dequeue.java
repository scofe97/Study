package 자바공부.자료구조.콜렉션;

import java.util.Deque;
import java.util.LinkedList;

public class EX_Dequeue {
    public static void main(String[] args) {

        Deque<Integer> deque = new LinkedList<>();


        deque.addFirst(5);
        deque.addLast(1);
        deque.offerFirst(2);
        deque.offerLast(4);
        System.out.println(deque); // [2, 5, 1, 4]


        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.pollFirst());
        System.out.println(deque.pollLast());
        // 2 4 5 1


        deque.add(1);
        deque.add(2);
        deque.add(3);
        deque.add(4);

        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());
        System.out.println(deque.peek());
        System.out.println(deque.peekLast());
        // 1 4 1 4

    }
}
