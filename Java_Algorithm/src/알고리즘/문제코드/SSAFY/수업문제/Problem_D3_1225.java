package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_D3_1225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            tc = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < 8; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            int step = 1;
            while(true){
                int temp = queue.poll();

                if(temp - step <= 0){
                    temp = 0;
                    queue.offer(temp);
                    break;
                }
                else{
                    temp -= step;

                    if(step == 5){
                        step = 1;
                    }else{
                        step++;
                    }

                    queue.offer(temp);
                }
            }

            System.out.print("#" + tc + " ");
            for (Integer integer : queue) {
                System.out.print(integer + " ");
            }
            System.out.println();


        }
    }
}
