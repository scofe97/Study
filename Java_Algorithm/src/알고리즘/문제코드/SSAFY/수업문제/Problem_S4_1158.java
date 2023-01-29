package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem_S4_1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int size = Integer.parseInt(st.nextToken());
        int step = Integer.parseInt(st.nextToken());

        List<Integer> list = IntStream.rangeClosed(1, size).boxed().collect(Collectors.toList());

        int temp = 0;
        while(true){
            temp = (temp+step-1) % list.size();
            sb.append(list.remove(temp));
            if(!list.isEmpty()){
                sb.append(", ");
            }else{
                break;
            }
        }
        sb.insert(0, "<");
        sb.append(">");
        System.out.println(sb);
    }
}
