package 알고리즘.문제코드.Eleven;

public class stack {


    public static void main(String[] args,  int[] A) {

        String S = "abbaa";
        char[] s = S.toCharArray();
        int cnt = 0;

        for (int i = 0; i < s.length; i++) {
            if(s[i] == s[(i + s.length -1) % (s.length + 1)]) cnt++;
        }
    }
}
