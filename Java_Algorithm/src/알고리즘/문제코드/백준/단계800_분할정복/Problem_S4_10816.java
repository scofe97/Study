package 알고리즘.문제코드.백준.단계800_분할정복;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Problem_S4_10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int size1 = Integer.parseInt(br.readLine());
        Map<Integer, Integer> list1 = new HashMap();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size1; i++) {
            int temp = Integer.parseInt(st.nextToken());
            int value = 1;
            if(list1.containsKey(temp)){
                value = list1.get(temp) + 1;
            }

            list1.put(temp, value);
        }

        int size2 = Integer.parseInt(br.readLine());
        int[] list2 = new int[size2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size2; i++) {
            list2[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = list1.keySet().stream().sorted().distinct().collect(Collectors.toList());

        int start;
        int end;
        int point;
        for (int i : list2) {
            start = 0;
            end = list.size()-1;

            while(true){
                point = (start+end)/2;

                if(start > end){
                    sb.append("0 ");
                    break;
                }
                else if(list.get(point) < i){
                    start = point + 1;
                }else if(list.get(point) > i){
                    end = point - 1;
                }else{
                    sb.append(list1.get(i)).append(" ");
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}
