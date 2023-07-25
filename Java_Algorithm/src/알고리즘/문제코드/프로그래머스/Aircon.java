package 알고리즘.문제코드.프로그래머스;

public class Aircon {

    public static void main(String[] args) {

    }

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {

        int answer = 0;


        int step_temerature = temperature;

        for (int i = onboard.length - 1; i >= 0; i--) {
            if (onboard[i] == 1) {
                if (step_temerature < t1 || step_temerature > t2) {
                    if (step_temerature < t1) {
                        int step = Math.abs(t1 - temperature);
                        answer = step * a;
                        step_temerature += step;
                        i -= step + 1;

                    }
                    if (step_temerature > t2) {
                        int step = Math.abs(t2 - temperature);
                        answer = step * a;
                        step_temerature -= step;
                        i -= step + 1;
                    }

                    else{
                        answer += b;
                    }

                }
            }
        }

        return answer;
    }
}
