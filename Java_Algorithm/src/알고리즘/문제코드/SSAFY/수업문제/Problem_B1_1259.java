package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Problem_B1_1259 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String temp = br.readLine();
        while (!temp.equals("0")) {

            Deque<Character> deque = new ArrayDeque<>();
            for (char c : temp.toCharArray()) {
                deque.add(c);
            }

            boolean check = true;
            while (deque.size() >= 2) {
                char f = deque.pollFirst();
                char b = deque.pollLast();

                if (b != f) {
                    check = false;
                    break;
                }
            }

            System.out.println(check ? "yes" : "no");

            temp = br.readLine();
        }
    }
}
