package 알고리즘.문제코드.Eleven;

public class stack {


    // 일정 간격의 글자가 같은가?
    public static void main(String[] args) {

        String S = "aaaab";
        char[] s = S.toCharArray();
        int cnt = 0;

        for (int i = 0; i < s.length; i++) {
            if(s[i] == s[(i + s.length -1) % (s.length)]) cnt++;
        }

        System.out.println(cnt);
    }
}
