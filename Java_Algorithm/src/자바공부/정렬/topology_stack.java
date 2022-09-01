package 자바공부.정렬;

import java.util.ArrayList;
import java.util.Stack;

public class topology_stack {
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
    public void create_graph(int v) {
        this.V = v;
        this.adj = new ArrayList<>();
        this.indegree = new int[v];

        for (int i = 0; i < V; i++) {
            this.adj.add(new ArrayList<>());
        }
    }

    public void add_edge(int source, int target) {
        if (source > this.V || target > this.V) {
            throw new NullPointerException();
        }

        this.adj.get(source)
                .add(target);
        this.indegree[target]++;
        this.E++;
    }

    // 위상 정렬을 수행하는 method
    public boolean topological_sort(int[] result) {
        // 현재 정점 크기의 bool 배열 추가
        boolean[] visited = new boolean[this.V];
        boolean[] beingVisited = new boolean[this.V];

        Stack<Integer> stack = new Stack<>();

        boolean sort_result = false;
        // 기존에 방문을 진행하지 않았다면 recursion을 통해 stack에 추가한다.
        for (int i = 0; i < this.V; i++) {
            if (!visited[i]) {
                sort_result = this.recursion(i, stack, visited, beingVisited);
            }

            if (!sort_result) {
                break;
            }
        }

        int index = 0;
        if (sort_result) {
            while (!stack.isEmpty()) {
                result[index++] = stack.pop();
            }
        }
        return sort_result;
    }

    // stack에 넣기 위해 자신이 가리키는 이전의 모든 정점을 우선적으로 stack에 넣는다.
    private boolean recursion(int v, Stack stack, boolean[] visited, boolean[] beingVisited) {
        beingVisited[v] = true;
        ArrayList<Integer> arrayList = this.adj.get(v);

        boolean isNotCycle = true;
        for (Integer target : arrayList) {
            if (beingVisited[v]) {
                return false;
            }

            if (!visited[v]) {
                recursion(v, stack, visited, beingVisited);
            }

            if (!isNotCycle) {
                return false;
            }
        }

        stack.push(v);
        visited[v] = true;
        beingVisited[v] = false;
        return true;
    }
}