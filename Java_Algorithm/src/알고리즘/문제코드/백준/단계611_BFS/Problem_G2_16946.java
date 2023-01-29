package 알고리즘.문제코드.백준.단계611_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Problem_G2_16946 {
    static int n; // 세로
    static int m; // 가로
    static char[][] graph; // 원본 배열
    static int[][] visited; // 원본 배열 0 그룹 생성

    static HashMap<Integer, Integer> group_map; // 그룹의 개수 확인 map
    static List<Point> wall_list;// 1의 위치 리스트

    static int[] dir_x = {1, 0, -1, 0};
    static int[] dir_y = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        visited = new int[n][m];

        wall_list = new ArrayList<>();
        group_map = new HashMap<>();

        int index = 2;

        // 그래프 초기화
        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        // 0 그룹화 하기 => 그룹 번호 부여, 같은 그룹 개수 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0 && graph[i][j] == '0') {
                    group_map.put(index, bfs(i, j, index));
                    index++;
                }
            }
        }

        // 그룹을 완성하면 4방향에 있는 그룹을 선택하고, 모두 더한다.
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(mapCount(i,j));
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int mapCount(int x, int y){
        int sum = 1;
        HashSet<Integer> hs = new HashSet<>();

        if(graph[x][y] == '0') {
            return 0;
        }

        for (int k = 0; k < 4; k++) {
            int step_x = x + dir_x[k];
            int step_y = y + dir_y[k];
            if (step_x < n && step_x >= 0 && step_y < m && step_y >= 0) {
                if(graph[step_x][step_y] == '0') { // 벽이 아닐때
                    hs.add(visited[step_x][step_y]);
                }
            }
        }

        for(int ind: hs) {
            sum += group_map.get(ind);
        }

        return sum%10;
    }


    // 그래프는 고정되어있으므로, 0의 그룹만 지어주고 개수만 체크하면
    // (1의 4방향의 그룹 + 1) % 10 로 처리할 수 있음
    static int bfs(int i, int j, int group) {
        Queue<Point> queue = new LinkedList<>();
        int count = 1;
        queue.offer(new Point(i, j, group));
        visited[i][j] = group;

        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            for (int k = 0; k < 4; k++) {
                int temp_move_x = temp.x + dir_x[k];
                int temp_move_y = temp.y + dir_y[k];
                if (temp_move_x < n && temp_move_x >= 0 && temp_move_y < m && temp_move_y >= 0) {
                    if (visited[temp_move_x][temp_move_y] == 0 && graph[temp_move_x][temp_move_y] == '0') {
                        visited[temp_move_x][temp_move_y] = temp.group;
                        queue.offer(new Point(temp_move_x, temp_move_y, temp.group));
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static class Point {
        int x;
        int y;
        int group;


        Point(int x, int y, int group) {
            this.x = x;
            this.y = y;
            this.group = group;
        }
    }
}

