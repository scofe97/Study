package 알고리즘.문제코드.SSAFY.수업_220818;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class AdjListTest {

    static class Node{
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    static Node[] adjList;
    static int N;
    static boolean visited[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int E = sc.nextInt();

        adjList = new Node[N];
        visited = new boolean[N];

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(to, adjList[to]);
        }

        dfs(0);
        System.out.println();
        bfs(0);
    }

    public static void dfs(int cur) {
        visited[cur] = true;
        System.out.println((char) (cur + 'A'));

        for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
            if (!visited[temp.to]) {
                dfs(temp.to);
            }
        }
    }

    public static void bfs(int start){
        // 0정점 시작
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        visited[0] = true;
        queue.offer(0);

        while (!queue.isEmpty()) {

            int cur = queue.poll();
            System.out.println((char) (cur + 'A'));

            for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
                if (!visited[temp.to]) {
                    visited[temp.to] = true;
                    queue.offer(temp.to);
                }
            }
        }
        System.out.println();
    }
}
