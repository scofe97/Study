package 알고리즘.문제코드.SSAFY.수업문제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem_S3_1244 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int size = Integer.parseInt(br.readLine());
        String[] arr;
        StringBuilder sb = new StringBuilder();
        arr = br.readLine().split(" ");

        int cnt = Integer.parseInt(br.readLine());
        int[][] student = new int[cnt][2];
        for (int i = 0; i < cnt; i++) {
            st = new StringTokenizer(br.readLine());
            student[i][0] = Integer.parseInt(st.nextToken());
            student[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < cnt; i++) {
            int num = student[i][1];

            if(student[i][0] == 1){
                for (int j = 0; j < size; j++) {
                    if((j+1) % num == 0){
                        arr[j] = arr[j].equals("0") ? "1" : "0";
                    }
                }
            }else if(student[i][0] == 2){
                num--;
                int p = 1;
                arr[num] = arr[num].equals("0") ? "1" : "0";
                while (true){
                    if(num + p >= size || num - p < 0){
                        break;
                    }

                    if(arr[num+p].equals(arr[num-p])){
                        arr[num+p] = arr[num+p].equals("0") ? "1" : "0";
                        arr[num-p] = arr[num-p].equals("0") ? "1" : "0";
                        p++;
                    }else{
                        break;
                    }
                }
            }

        }

        for (int i = 0; i < arr.length; i++) {
            if(i != 0 && i % 20 == 0){
                sb.append("\n");
            }
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb.toString().trim());
    }
}
