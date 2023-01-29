package 알고리즘.문제코드.SSAFY.수업_220822;

public class UnionFind2 {

    static int n;
    static int[] parents;

    public static void main(String[] args) {
        n = 10;
        parents = new int[n+1];

        int[][] graph = {{1,2},{2,3},{3,4},{5,6},{6,7},{7,8},{9,10}};
        System.out.println("< 연결 전 >");

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            System.out.print(parents[i] + " ");
        }

        //노드 연결
        for(int i=0; i<graph.length; i++) {
            int a = graph[i][0];
            int b = graph[i][1];

            union(a,b);
        }

        //연결 후
        System.out.println("< 연결 후 >");
        for (int i = 1; i <= n; i++) {
            System.out.print(parents[i] + " ");
        }
        System.out.println();
    }

    static int find(int x){
        if(parents[x] == x) return x;
        return find(parents[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x < y){
            parents[y] = x;
        }else{
            parents[x] = y;
        }
    }
}
