package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Problem_D4_1218 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {

            int size = Integer.parseInt(br.readLine());
            char[] list = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();

            for (Character c : list) {
                if (!stack.isEmpty() && stack.peek() == '(' && c == ')') {
                    stack.pop();
                } else if (!stack.isEmpty() && stack.peek() == '[' && c == ']') {
                    stack.pop();
                } else if (!stack.isEmpty() && stack.peek() == '{' && c == '}') {
                    stack.pop();
                } else if (!stack.isEmpty() && stack.peek() == '<' && c == '>') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }

            if(stack.isEmpty()){
                System.out.printf("#%d %d\n",tc,1);
            }else{
                System.out.printf("#%d %d\n",tc,0);
            }
        }
    }
}
