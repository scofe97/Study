package 알고리즘.문제코드.백준.단계710_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Problem_G3_2109 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = Integer.parseInt(br.readLine());
        int max_day = 0;
        List<Lecture> list = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int pay = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            max_day = Math.max(max_day, day);
            list.add(new Lecture(pay, day));
        }


        PriorityQueue<Lecture> queue = new PriorityQueue<>(list);

        boolean[] checked = new boolean[max_day+1];
        int result = 0;

        while(!queue.isEmpty()){
            Lecture temp = queue.poll();

            for (int i = temp.day; i > 0 ; i--) {
                if(!checked[i]){
                    checked[i] = true;
                    result += temp.pay;
                    break;
                }
            }
        }

        System.out.println(result);



    }

    static class Lecture implements Comparable<Lecture> {
        int pay;
        int day;

        Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.pay == o.pay) {
                return this.day - o.day;
            }

            return o.pay - this.pay;
        }
    }
}
