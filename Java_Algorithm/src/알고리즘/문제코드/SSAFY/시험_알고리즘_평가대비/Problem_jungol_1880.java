package 알고리즘.문제코드.SSAFY.시험_알고리즘_평가대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1153
public class Problem_jungol_1880 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Map<Character, Character> map = new HashMap<>();
        char step = 'a';
        for (char c : br.readLine().toLowerCase().toCharArray()) {
            map.put(step++, c);
        }

        for (char c : br.readLine().toCharArray()) {
            char temp = 0;
            if(Character.isUpperCase(c)){
                temp = Character.toUpperCase(map.getOrDefault(Character.toLowerCase(c), ' '));
            }else{
                temp = map.getOrDefault(c, ' ');
            }
            sb.append(temp);
        }

        System.out.println(sb);
    }
}
