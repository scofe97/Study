package 알고리즘.문제코드.SSAFY.시험_A형대비.삼성A형_기출;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_G4_17471 {

    static int n;
    static int[] people;
    static int[] area;
    static boolean[] visited;
    static List<List<Integer>> adjList;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n  = Integer.parseInt(br.readLine());
        people = new int[n+1];
        area = new int[n+1];
        visited = new boolean[n+1];
        adjList = new ArrayList<>();
        result = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            adjList.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                adjList.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        dfs(1);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    static void dfs(int step){
        if(step == n + 1){
            int area1 = 0;
            int area2 = 0;

            for (int i = 1; i <= n; i++) {
                if(area[i] == 1) area1 += people[i];
                else area2 += people[i];
            }

            visited = new boolean[n + 1];
            int link = 0; // 연결된 지역들의 개수를 셈
            for (int i = 1; i <= n; i++) {
                if(!visited[i]){
                    bfs(i, area[i]);
                    link++;
                }
            }

            if(link == 2) result = Math.min(result, Math.abs(area1 - area2));
            return;
        }

        area[step] = 1;
        dfs(step+1);

        area[step] = 2;
        dfs(step+1);

    }

    static void bfs(int idx, int areaName){
        Queue<Integer> queue = new LinkedList<>();
        visited[idx] = true;
        queue.offer(idx);

        while(!queue.isEmpty()){
            int temp = queue.poll();

            for (Integer i : adjList.get(temp)) {
                if(area[i] == areaName && !visited[i]){
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }
}
