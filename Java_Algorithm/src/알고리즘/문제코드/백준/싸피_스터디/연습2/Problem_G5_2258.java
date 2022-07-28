package 알고리즘.문제코드.백준.싸피_스터디.연습2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Problem_G5_2258 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int meat_cnt = Integer.parseInt(st.nextToken());
        int need_cnt = Integer.parseInt(st.nextToken());

        List<Meat> meat_list = new ArrayList<>();
        for (int i = 0; i < meat_cnt; i++) {
            st = new StringTokenizer(br.readLine());
            meat_list.add(new Meat(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(meat_list);

        int totalPrice = -1;
        int totalGram = 0;
        int answer = Integer.MAX_VALUE;
        boolean isPossible = false;

        for (int i = 0; i < meat_cnt; i++) {

            totalGram += meat_list.get(i).weight;

            if (i > 0 && meat_list.get(i-1).value == meat_list.get(i).value) {
                totalPrice += meat_list.get(i).value;
            } else {
                totalPrice = meat_list.get(i).value;
            }

            if (totalGram >= need_cnt) {
                isPossible = true;
                answer = Math.min(answer, totalPrice);
            }
        }

        System.out.println(isPossible ? answer : "-1");

    }

    static class Meat implements Comparable<Meat>{
        int weight;
        int value;

        Meat(int weight, int value){
            this.weight = weight;
            this.value = value;
        }


        @Override
        public int compareTo(Meat o) {
            if(this.value == o.value){
                return o.weight - this.weight;
            }else{
                return this.value - o.value;
            }
        }
    }
}
