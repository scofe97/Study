package 알고리즘.문제코드.백준.단계710_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G2_1202 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Point> j_list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            j_list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(j_list);

        int[] bag_list = new int[k];
        for (int i = 0; i < k; i++) {
            bag_list[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bag_list);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        long sum = 0;
        for (int i = 0,  j = 0; i < k; i++) {
            while(j < n && bag_list[i] >= j_list.get(j).m){
                queue.offer(j_list.get(j++).v);
            }

            if(!queue.isEmpty()){
                sum += queue.poll();
            }
        }

        System.out.println(sum);

    }

    static class Point implements Comparable<Point>{
        int m;
        int v;

        Point(int m, int v){
            this.m = m;
            this.v = v;
        }


        @Override
        public int compareTo(Point o) {
            if(this.m == o.m){
                return o.v - this.v;
            }else{
                return this.m - o.m;
            }
        }
    }
}
