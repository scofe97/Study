
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_D4_1238 {

    static HashSet<Integer> visited;
    static HashMap<Integer, HashSet<Integer>> graph;
    static TreeMap<Integer, ArrayList<Integer>> result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            ArrayList<Integer> start = new ArrayList<Integer>(Arrays.asList(Integer.parseInt(st.nextToken())));

            st = new StringTokenizer(br.readLine());
            graph = new HashMap<>();
            result = new TreeMap<>(Collections.reverseOrder());
            visited = new HashSet<>();

            while(st.hasMoreTokens()){
                int v_from = Integer.parseInt(st.nextToken());
                int v_to = Integer.parseInt(st.nextToken());

                if(graph.containsKey(v_from)){
                    graph.get(v_from).add(v_to);
                }else{
                    graph.put(v_from, new HashSet<Integer>(Arrays.asList(v_to)));
                }
            }
            bfs(start.get(0));
            int result2 = 0;

            for (Integer v : result.get(result.firstKey())) {
                result2 = Math.max(result2, v);
            }

            System.out.println("#"+tc+" "+result2);
            result2 = 0;
        }
    }

    static void bfs(int start){
        Queue<ArrayList<Integer>> queue = new LinkedList<>();
        queue.offer(new ArrayList<Integer>(Arrays.asList(start, 0)));

        while(!queue.isEmpty()){
            ArrayList<Integer> p = queue.poll();
            int vertex = p.get(0);
            int step = p.get(1);

            if(result.containsKey(step)){
                result.get(step).add(vertex);
            }else{
                result.put(step, new ArrayList<>(Arrays.asList(vertex)));
            }

            HashSet<Integer> vertex_to = graph.get(vertex);
            if(vertex_to != null){
                for (Integer v : vertex_to) {
                    if(!visited.contains(v)){
                        visited.add(v);
                        queue.offer(new ArrayList<Integer>(Arrays.asList(v, step+1)));
                    }
                }
            }
        }
    }
}
