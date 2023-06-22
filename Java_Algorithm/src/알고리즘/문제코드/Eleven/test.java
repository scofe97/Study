package 알고리즘.문제코드.Eleven;

public class test {
    public int solution(int[] A) {
        boolean[] number = new boolean[1000002];
        for(int index : A){

            if(index < 0 ){
                continue;
            }
            else{
                number[index] = true;
            }
        }

        for(int i=1; i<100003; i++){
            if(number[i]) return i;
        }

        return 0;
    }
}
