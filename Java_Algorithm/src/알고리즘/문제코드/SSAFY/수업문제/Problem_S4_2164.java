package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Problem_S4_2164 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= size; i++) {
            queue.offer(i);
        }

        while(true){
            int temp = queue.poll();
            if(queue.isEmpty()){
                System.out.println(temp);
                return;
            }

            temp = queue.poll();
            if(queue.isEmpty()){
                System.out.println(temp);
                return;
            }
            queue.offer(temp);
        }
    }
}
