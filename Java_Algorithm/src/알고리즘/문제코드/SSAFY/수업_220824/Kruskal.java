package 알고리즘.문제코드.SSAFY.수업_220824;

import java.util.ArrayList;
import java.util.Collections;

class Edge implements Comparable<Edge> {
    int n1;         // 정점1 (무방향 그래프라 시작, 종료 정점 구분 X)
    int n2;         // 정점2
    int cost;       // 비용

    Edge(int n1, int n2, int cost){
        this.n1 = n1;
        this.n2 = n2;
        this.cost = cost;
    }

    // 비용으로 오름차순 정렬하기 위한 Comparable method
    @Override
    public int compareTo(Edge edge) {
        return edge.cost >= this.cost ? -1 : 1;
    }
}
public class Kruskal {
    public static ArrayList<Edge> list = new ArrayList<Edge>();
    public static int[] parent;

    public static void main(String[] args) {
        list.add(new Edge(1, 2, 5));
        list.add(new Edge(2, 3, 1));
        list.add(new Edge(3, 6, 9));
        list.add(new Edge(1, 4, 3));
        list.add(new Edge(1, 3, 8));
        list.add(new Edge(1, 5, 10));
        list.add(new Edge(4, 5, 4));
        list.add(new Edge(5, 6, 12));
        list.add(new Edge(4, 6, 2));
        list.add(new Edge(4, 3, 4));

        parent = new int[6+1];

        // hint 각 정점의 부모를 자신으로 설정
        for (int i = 1; i < parent.length; i++) {
            parent[i] = i;
        }
        Collections.sort(list);
        int sum = 0;

        // hint 정렬된 간선을 합치고, 만약 사이클이 있다면 넘김
        for (int i = 0; i < list.size(); i++) {
            Edge edge = list.get(i);

            if(!isConnect(edge.n1, edge.n2)){
                System.out.println(sum);
                sum += edge.cost;
                union(edge.n1 , edge.n2);
            }else{
                System.out.println("cycle");
            }
        }

        System.out.println(sum);
    }

    static boolean isConnect(int n1, int n2){
        int n1Root = find(n1);
        int n2Root = find(n2);

        if(n1Root == n2Root) return true;
        else return false;
    }

    static int find(int x){
        if(parent[x] == x) return x;
        else return find(parent[x]);
    }

    static void union(int n1, int n2){
        int n1Root = find(n1);
        int n2Root = find(n2);

        if(n1Root < n2Root) parent[n2Root] = n1Root;
        else parent[n1Root] = n2Root;
    }
}
