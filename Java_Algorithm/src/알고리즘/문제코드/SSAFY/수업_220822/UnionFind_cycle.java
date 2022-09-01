package 알고리즘.문제코드.SSAFY.수업_220822;

public class UnionFind_cycle {

    static int n;
    static int[] parents;

    public static void main(String[] args) {
        n = 10;
        parents = new int[n + 1]; // 값 초기화 및 노드 연결 전

        int[][] graph = {{1,2},{2,3},{3,4},{1,4}};
        System.out.println("< 연결 전 >");

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            System.out.print(parents[i] + " ");
        }
        System.out.println();

        //노드 연결 // 사이클 판별하기
        for(int i=0; i<graph.length; i++) {
            int a = graph[i][0];
            int b = graph[i][1];

            if(isCycle(a,b)) {
                System.out.println("cycle!");
                break;
            }else {
                union(a,b);
            }

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
        return parents[x] = find(parents[x]);
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

    static boolean isCycle(int x, int y){
        boolean result = false;
        if(parents[x] == parents[y]) result = true;
        return result;
    }
}
