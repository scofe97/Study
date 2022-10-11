package 알고리즘.문제코드.SSAFY.수업_221011;

public class findString {

    public static void main(String[] args) {
        String ws = "AABDCDABD";
        String ps = "ABD";
        findString(ws, ps);
    }

    private static void findString(String ws, String ps){
        int wsSize = ws.length();
        int psSize = ps.length();

        int wsHash = 0;
        int psHash = 0;
        int power = 1; // 제곱수

        for (int i = 0; i <= wsSize - psSize; i++) {
            if (i == 0) {
                for (int j = 0; j < psSize; j++) {
                    wsHash += ws.charAt(psSize - 1 - j) * power;
                    psHash += ps.charAt(psSize - 1 - j) * power;
                    if (j < psSize - 1) power *= 3;
                }
            }
            else {
                wsHash = 3 * (wsHash - ws.charAt(i - 1) * power) + ws.charAt(psSize - 1 + i);
            }
            if (wsHash == psHash) {
                boolean isFind = true;
                // 우연히 해시값이 겹친 경우 위해 문자열 일치 여부 검사
                for (int j = 0; j < psSize; j++) {
                    if (ws.charAt(i + j) != ps.charAt(j)){
                        isFind = false;
                        break;
                    }
                }
                if (isFind) {
                    System.out.println(wsHash + " " + psHash);
                    System.out.println(i+1 + "번째에서 발견");
                }
            }
        }
    }
}
