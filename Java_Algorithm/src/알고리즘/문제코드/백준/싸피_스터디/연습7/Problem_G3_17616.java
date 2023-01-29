package 알고리즘.문제코드.백준.싸피_스터디.연습7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G3_17616 {
    static boolean check[][];
    static List<Integer> li[][];
    static int num,query,student;

    static int spread(int flag){
        Queue<Integer> q = new LinkedList<>();
        check[student][flag]=true;
        q.offer(student);
        int cnt=0;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int i=0;i<li[cur][flag].size();i++){
                int next = li[cur][flag].get(i);
                if(!check[next][flag]){
                    check[next][flag]=true;
                    q.offer(next);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main (String[] args) throws java.lang.Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        num = Integer.parseInt(st.nextToken());
        query = Integer.parseInt(st.nextToken());
        student = Integer.parseInt(st.nextToken());

        //초기화
        check = new boolean[num+1][2];
        li = new List[num+1][2];
        for(int i=1;i<=num;i++){
            li[i][0] = new ArrayList<>();
            li[i][1] = new ArrayList<>();
        }
        for(int i=0;i<query;i++){
            str = br.readLine();
            st = new StringTokenizer(str);
            int pre = Integer.parseInt(st.nextToken());
            int post = Integer.parseInt(st.nextToken());
            li[pre][0].add(post);
            li[post][1].add(pre);
        }
        int best = 1+spread(1);
        int worst = num-(spread(0));
        System.out.println(best+" "+worst);
    }
}
