package 자바공부.정렬;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class topology_queue {
    public static void main(String[] args) {
        int n = 6;
        // Cycle이 없는 경우
        topology_queue graph = new topology_queue();

        graph.create_graph(n);
        graph.add_edge(2, 3);
        graph.add_edge(3, 1);
        graph.add_edge(4, 0);
        graph.add_edge(4, 1);
        graph.add_edge(5, 0);
        graph.add_edge(5, 2);

        int[] result = new int[n];
        boolean sort_result = graph.topological_sort(result);
        if(sort_result){
            for(int i : result){
                System.out.print(i + ", ");
            }
            System.out.println();
        } else {
            System.out.println("Cycle이 발생했습니다!");
        }


        // Cycle이 있는 경우
        graph = new topology_queue();

        graph.create_graph(n);
        graph.add_edge(2, 3);
        graph.add_edge(3, 1);
        graph.add_edge(4, 0);
        graph.add_edge(4, 1);
        graph.add_edge(5, 0);
        graph.add_edge(5, 2);
        // 1이 5를 가리키는 상황을 추가!
        // 이 부분에 의해 1 - 5 - 3이 서로 Cycle을 이루게 됨!
        graph.add_edge(1, 5);

        result = new int[n];
        sort_result = graph.topological_sort(result);
        if(sort_result){
            for(int i : result){
                System.out.print(i + ", ");
            }
            System.out.println();
        } else {
            System.out.println("Cycle이 발생했습니다!");
        }
    }

    private int V;  // 정점의 개수
    private int E;  // 간선의 개수
    private ArrayList<ArrayList<Integer>> adj; // 그래프를 표현하는 인접 리스트
    private int[] indegree;


    // 그래프 생성
    public void create_graph(int v){
        this.V = v;
        this.adj = new ArrayList<>();
        this.indegree = new int[v];

        for (int i = 0; i < V; i++) {
            this.adj.add(new ArrayList<>());
        }
    }

    public void add_edge(int source, int target){
        if(source > this.V || target > this.V){
            throw new NullPointerException();
        }

        this.adj.get(source).add(target);
        this.indegree[target]++;
        this.E++;
    }

    public boolean topological_sort(int[] result){
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < this.V; i++) {
            if(this.indegree[i] == 0){
                q.offer(i);
            }
        }

        int idx = 0;
        while(!q.isEmpty()){
            int current = q.poll();
            result[idx++] = current;

            for (int target : this.adj.get(current)) {
                this.indegree[target]--;

                if(this.indegree[target] == 0){
                    q.add(target);
                }
            }
        }

        if(this.V != idx){
            return false;
        }

        return true;
    }
}
