package 알고리즘.문제코드.Eleven;

public class stack3 {

    // 연속된 문자열중 최대값을 제외하고 모두 제거한다.
    public static void main(String[] args) {

        String S = "aabaa";
        int[] C = {1,1,0,2,2};
        char[] s = S.toCharArray();

        int prevIndex = 0;
        int result = 0;

        for (int i = 1; i < s.length; i++) {
            if(s[prevIndex] == s[i]){
                result += Math.min(C[i], C[prevIndex]);
                if(C[prevIndex] < C[i]) prevIndex = i;
            }
            else prevIndex = i;
        }

        System.out.println(result);
    }
}
