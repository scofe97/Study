package 알고리즘.문제코드.백준.단계611_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Problem_G4_12886 {
    static int result = 0;
    static boolean[][] visited;

    // 500 499 500
    // 1 998 500
    // 1 498 1000
    // 1 996 502
    // 1 494 1004

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        visited = new boolean[1501][1501];
        int sum = a + b + c;

        bfs(new Point(a, b, c));
        System.out.println(result);

    }

    static void bfs(Point init) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(init);

        visited[init.x][init.y] = true;
        visited[init.y][init.x] = true;
        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (temp.z == temp.y && temp.y == temp.x) {
                result = 1;
                return;
            }

            if (temp.x != temp.y) {
                int nx = temp.x > temp.y ? temp.x - temp.y : temp.x * 2;
                int ny = temp.x > temp.y ? temp.y * 2 : temp.y - temp.x;

                if (!visited[nx][ny]) {
                    queue.offer(new Point(nx, ny, temp.z));
                    visited[nx][ny] = true;
                    visited[ny][nx] = true;
                }
            }

            if (temp.x != temp.z) {
                int nx = temp.x > temp.z ? temp.x - temp.z : temp.x * 2;
                int nz = temp.x > temp.z ? temp.z * 2 : temp.z - temp.x;

                if (!visited[nx][nz]) {
                    queue.offer(new Point(nx, temp.y, nz));
                    visited[nx][nz] = true;
                    visited[nz][nx] = true;
                }
            }

            if (temp.y != temp.z) {
                int ny = temp.y > temp.z ? temp.y - temp.z : temp.y * 2;
                int nz = temp.y > temp.z ? temp.z * 2 : temp.z - temp.y;

                if (!visited[ny][nz]) {
                    queue.offer(new Point(temp.x, ny, nz));
                    visited[ny][nz] = true;
                    visited[nz][ny] = true;
                }
            }
        }

    }


    static class Point {
        int x;
        int y;
        int z;

        Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

}
