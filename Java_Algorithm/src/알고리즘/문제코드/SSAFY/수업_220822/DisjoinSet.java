package 알고리즘.문제코드.SSAFY.수업_220822;

import java.util.Arrays;

public class DisjoinSet {
    static int[] parent;

    public static void main(String[] args) {
        // makeSet
        parent = new int[5];
        // 각 인덱스(=노드)는 자기 자신을 가리키고 있다
        parent = MakeSet(5);
        System.out.println(Arrays.toString(parent));

        // 1이 포함된 집합, 2가 포함된 집합 합치기.
        union(1, 2);
        System.out.println(Arrays.toString(parent));
        // 2가 포함된 집합, 3이 포함된 집합 합치기.
        union(2, 3);
        System.out.println(Arrays.toString(parent));
        // 4가 포함된 집합, 5가 포함된 집합 합치기.
        union(4, 5);
        System.out.println(Arrays.toString(parent));
        // 3이 포함된 집합, 5가 포함된 집합 합치기.
        union(3, 5);
        System.out.println(Arrays.toString(parent));

        // Find 연산을 통한 각 노드가 속한 집합 확인.
        System.out.println(find(1));
        System.out.println(find(2));
        System.out.println(find(3));
        System.out.println(find(4));
        System.out.println(find(5));
    }

    // Union
    private static void union(int a, int b) {
        // 각 집합을 대표하는 부모가 다른 부모로 편입 되어야 한다. 원소가 편입되어서는 안된다.
        a = find(a);
        b = find(b);
        // 일반적으로 더 작은 값으로 다른 집합이 편입되도록 한다.
        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }

    // Find
    private static int find(int x) {
        // 만일, 찾는 대상과 인덱스 번호가 같다면 그 인덱스(=노드)가 해당 집합의 부모이다.
        if (parent[x] == x)
            return x;
            // 경로 압축
        else
            return find(parent[x]);
    }

    // makeSet
    private static int[] MakeSet(int size) {
        // 각 인덱스에 번호가 대응하도록 사이즈를 1더하여 배열 선언.
        int[] arr = new int[size + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }
}