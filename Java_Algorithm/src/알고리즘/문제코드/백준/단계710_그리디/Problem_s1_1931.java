package 알고리즘.문제코드.백준.단계710_그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Problem_s1_1931 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(br.readLine());
        ArrayList<Point> meeting_list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meeting_list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(meeting_list);

        int day = 0;
        int result = 0;
        for (Point point : meeting_list) {
            if(day <= point.start){
                day = point.end;
                result++;
            }
        }

        System.out.println(result);


    }

    static class Point implements Comparable<Point>{

        int start;
        int end;

        Point(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Point p){
            if(this.end == p.end){
                return this.start - p.start;
            }else{
                return this.end - p.end;
            }
        }
    }
}
