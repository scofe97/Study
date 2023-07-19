package 알고리즘.문제코드.이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class _16654 {

    static int N;
    static int L;
    static StringBuilder sb;
    static ArrayList<Integer> numList;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String[] s = br.readLine()
                .split(" ");

        N = Integer.parseInt(s[0]);
        L = Integer.parseInt(s[1]);

        numList = new ArrayList<>();
        s = br.readLine().split(" ");
        for (String s1 : s) {
            numList.add(Integer.parseInt(s1));
        }
        Collections.sort(numList);
        visited = new boolean[numList.get(numList.size() -1) + 1];

        dfs(0,  new ArrayList<>());
        System.out.println(sb);
    }

    private static void dfs(int step, ArrayList<Integer> list){
        if(list.size() == L){
            for (Integer i : list) {
                sb.append(i)
                        .append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int temp : numList) {
            if (!visited[temp]) {
                visited[temp] = true;
                list.add(temp);
                dfs(step + 1, list);
                list.remove(list.size() - 1);
                visited[temp] = false;
            }
        }
    }

}
