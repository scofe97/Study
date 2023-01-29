package 알고리즘.문제코드.사전학습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Problem_D5_1247 {

    static Point start;
    static Point end;
    static int vertex;
    static int result = Integer.MAX_VALUE;
    static boolean[] visited;
    static ArrayList<Point> vertex_list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            vertex = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            vertex_list = new ArrayList<>();
            while (st.hasMoreTokens()){
                vertex_list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            start = vertex_list.get(0);
            end = vertex_list.get(1);
            visited = new boolean[vertex];
            vertex_list.remove(0);
            vertex_list.remove(0);

            dfs(0, start, 0);
            System.out.println("#" + tc + " " +result);
            result = Integer.MAX_VALUE;
        }
    }

    static void dfs(int step, Point cur, int move){
        if(step == vertex){
            move += Math.abs(cur.x - end.x) + Math.abs(cur.y - end.y);
            result = Math.min(result, move);
            return;
        }

        for (int i = 0; i < vertex; i++) {
            if(!visited[i]){
                visited[i] = true;
                int temp = move + Math.abs(cur.x - vertex_list.get(i).x) + Math.abs(cur.y - vertex_list.get(i).y);
                dfs(step+1, vertex_list.get(i), temp);
                visited[i] = false;
            }
        }
    }
}

class Point {
    int x = 0;
    int y = 0;

    public Point(int a, int b) {
        x = a;
        y = b;
    }
}

