package 알고리즘.문제코드.SSAFY.수업_220826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TopologyTest {

    static class Node{
        int vertex;
        Node next;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }

    static int V, E;
    static Node[] adjList;
    static int[] inDegree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new Node[V+1];
        inDegree = new int[V+1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from] = new Node(to, adjList[from]);
            inDegree[to]++;
        }
        ArrayList<Integer> list = topologySort();

        if(list.size() == V){
            System.out.println(list);
        }else{
            System.out.println("Cycle");
        }
    }

    private static ArrayList<Integer> topologySort(){
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        // 진입차수가 0인 정점 큐에 넣기

        for (int i = 1; i <= V; i++) {
            if(inDegree[i] == 0) queue.offer(i);
        }
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            list.add(cur);

            for (Node temp = adjList[cur]; temp != null ; temp = temp.next) {
                if(--inDegree[temp.vertex] == 0) queue.offer(temp.vertex);
            }
        }
        return list;
    }
}
