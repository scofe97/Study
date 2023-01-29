package 알고리즘.문제코드.SSAFY.수업_220816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// http://jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1101&sca=3050
public class Problem_1828 {

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        int p = Integer.MIN_VALUE;
        for (Point point : list) {
            if(p < point.x){
                result++;
                p = point.y;
            }
        }

        System.out.println(result);
    }

    static class Point implements Comparable<Point> {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return this.y  - o.y;
        }
    }
}
