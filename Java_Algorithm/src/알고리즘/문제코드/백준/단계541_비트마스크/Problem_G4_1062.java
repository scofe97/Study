package 알고리즘.문제코드.백준.단계541_비트마스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Problem_G4_1062 {

    static ArrayList<String> sentence;
    static int word;
    static int count;
    static int result;
    static int mask;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        sentence = new ArrayList<>();
        word = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());
        result = 0;
        mask = 0;

        mask |= 1 << 'a' - 'a';
        mask |= 1 << 'n' - 'a';
        mask |= 1 << 't' - 'a';
        mask |= 1 << 'i' - 'a';
        mask |= 1 << 'c' - 'a';


        for (int i = 0; i < word; i++) {
            String temp = br.readLine();
            sentence.add(temp.substring(4, temp.length()-4));
        }
        if(count < 5){
            System.out.println(result);
            return;
        }else if(count >= 26){
            System.out.println(word);
            return;
        }else{
            dfs(0, 0);
        }

        System.out.println(result);
    }

    static void dfs(int step, int start){
        if(step + 5 == count){
            int temp = 0;

            for (int i = 0; i < sentence.size(); i++) {
                boolean flag = true;
                for (int j = 0; j < sentence.get(i).length(); j++) {
                    if((mask & (1 << sentence.get(i).charAt(j) - 'a')) == 0){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    temp += 1;
                }
            }

            result = Math.max(temp, result);
            return;
        }

        for (int j = start; j < 26; j++) {
            if((mask & (1 << j)) == 0){
                mask |= 1 << j;
                dfs(step+1, j+1);
                mask ^= 1 << j;
            }
        }
    }
}
