package 알고리즘.문제코드.백준.싸피_스터디.연습4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Problem_G4_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        int[] list = new int[size];

        for (int i = 0; i < size; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        List<Integer> sort_plus_list = Arrays.stream(list).filter(i -> i > 0).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        List<Integer> sort_minus_list = Arrays.stream(list).filter(i -> i <= 0).boxed().sorted().collect(Collectors.toList());

        Deque<Integer> queue_minus = new ArrayDeque<>(sort_minus_list);
        Deque<Integer> queue_plus = new ArrayDeque<>(sort_plus_list);

        int result = 0;

        while(!queue_minus.isEmpty()){
            if(queue_minus.size() == 1){
                result += queue_minus.poll();
                break;
            }

            int temp1 = queue_minus.poll();
            int temp2 = queue_minus.poll();
            result += temp1 * temp2;

        }
        while(!queue_plus.isEmpty()){
            if(queue_plus.size() == 1){
                result += queue_plus.poll();
                break;
            }

            int temp1 = queue_plus.poll();
            int temp2 = queue_plus.poll();

            if(Math.abs(temp1) == 1 || Math.abs(temp2)  == 1){
                result += temp1;
                result += temp2;
            }else{
                result += temp1 * temp2;
            }
        }

        System.out.println(result);

    }
}
