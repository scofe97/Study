
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Problem_D5_1248 {

    static ArrayList<Integer> visited1;
    static ArrayList<Integer> visited2;
    static int root;
    static int count;
    static HashMap<Integer, Integer> reverse_tree;
    static HashMap<Integer, ArrayList<Integer>> tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());;
            int v2 = Integer.parseInt(st.nextToken());;

            visited1 = new ArrayList<Integer>();
            visited2 = new ArrayList<Integer>();
            count = 1;

            reverse_tree = new HashMap<>();
            tree = new HashMap<>();

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()){
                int e1 = Integer.parseInt(st.nextToken());
                int e2 = Integer.parseInt(st.nextToken());

                if(tree.containsKey(e1)){
                    tree.get(e1).add(e2);
                }else{
                    tree.put(e1, new ArrayList<>(Arrays.asList(e2)));
                }

                reverse_tree.put(e2,e1);
            }

            reverse_dfs(v1,v2);
            dfs(root);
            System.out.println("#" + tc + " " + root + " " + count);
        }
    }
    static void reverse_dfs(int a, int b){
        if(a == 0 && b == 0 ) return;

        int temp_a = 0;
        int temp_b = 0;

        if(reverse_tree.containsKey(a)){
            temp_a = reverse_tree.get(a);
            visited1.add(temp_a);
        }

        if(reverse_tree.containsKey(b)){
            temp_b = reverse_tree.get(b);
            visited2.add(temp_b);
        }

        if(b != 0 && visited1.contains(b)){
            root = b;
            return;
        }

        if(a != 0 && visited2.contains(a)){
            root = a;
            return;
        }


        reverse_dfs(temp_a, temp_b);

    }

    static void dfs(int root){
        if(tree.containsKey(root)){
            for (Integer i : tree.get(root)) {
                count++;
                dfs(i);
            }
        }
    }
}