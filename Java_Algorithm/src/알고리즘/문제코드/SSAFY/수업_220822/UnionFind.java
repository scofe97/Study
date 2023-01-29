package 알고리즘.문제코드.SSAFY.수업_220822;

import java.util.Arrays;

public class UnionFind {

    public static void main(String[] args) {
        int[] set = new int[5];

        for (int i = 0; i < 5; i++) {
            set[i] = i;
        }

        set[0] = 1;
        set[2] = 3;
        set[3] = 4;

        union(set, 1, 4);
        System.out.println(Arrays.toString(set));
        // [1, 1, 3, 4, 1]
    }

    static int find(int[] parent, int i){
        if(parent[i] == i){
            return i;
        }
        return find(parent, parent[i]);
    }

    static void union(int[] parent, int a, int b){
        int a_parent = find(parent, a);
        int b_parent = find(parent, b);

        if(a_parent < b_parent)
            parent[b_parent] = a_parent;
        else
            parent[a_parent] = b_parent;
    }
}
