package 알고리즘.문제코드.Eleven;

public class stack2 {

    // B가 1개, N이 2개, A가 3개 인 조합이 최소 몇개나오는가?
    public static void main(String[] args) {

        String S = "BBBBAAAAANNNNNN";
        int bCnt = 0;
        int nCnt = 0;
        int aCnt = 0;

        int result = 0;

        char[] s = S.toCharArray();
        for (char c : s) {
            if(c == 'B') bCnt++;
            else if(c == 'N') nCnt++;
            else if(c == 'A') aCnt++;
        }

        System.out.println(Math.min(bCnt,  Math.min(nCnt/2, aCnt/3)));
    }
}
