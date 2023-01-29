package 자바공부.자료구조;

import java.util.Stack;

public class EX_Stack {
    public static void main(String[] args) {

        // n 1. 초기화
        Stack<Integer> stack1 = new Stack<>();
        Stack<String> stack2 = new Stack<>();


        // n 2. 추가, 삭제, 출력
        stack1.push(1);
        stack1.add(1, 2);
        stack1.push(3);
        System.out.println(stack1);

        System.out.println(stack1.pop()); // 최상단 값 출력 (제거)
        System.out.println(stack1.peek()); // 최상단 값 출력 (제거안함)


        // n 3. 기타 메서드
        System.out.println(stack1.size()); // 2
        System.out.println(stack1.empty()); // false
        System.out.println(stack1.contains(1)); // true
    }
}
