package 알고리즘.문제코드.SSAFY.수업_221007;

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

    static void dfs(int cnt){
        if(cnt == n + 1){
            int area1 = 0;
            int area2 = 0;

            for (int i = 1; i <= n; i++) {
                if(area[i] == 1) area1 += people[i];
                else if(area[i] == 2) area2 += people[i];
            }

            int link = 0;
            visited = new boolean[n+1];
            for (int i = 1; i <= n; i++) {
                if(!visited[i]){
                    bfs(i, area[i]);
                    link++;
                }
            }

            if(link == 2) result = Math.min(result, Math.abs(area1 - area2));
            return;
        }

        area[cnt] = 1;
        dfs(cnt + 1);

        area[cnt] = 2;
        dfs(cnt + 1);
    }

    static void bfs(int vertex, int areaNum){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(vertex);
        visited[vertex] = true;

        while (!queue.isEmpty()){
            int temp = queue.poll();

            for (Integer integer : adjList.get(temp)) {
                if(!visited[integer] && areaNum == area[integer]){
                    visited[integer] = true;
                    queue.offer(integer);
                }
            }
        }
    }
}
