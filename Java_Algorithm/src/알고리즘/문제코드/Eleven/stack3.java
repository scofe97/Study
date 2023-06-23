package 알고리즘.문제코드.Eleven;

public class stack2 {


    public static void main(String[] args,  int[] A) {

        String S = "NAANAAXNABABYNNBZ";
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

        Math.min(bCnt,  Math.min(nCnt/2, aCnt/3));
    }
}
