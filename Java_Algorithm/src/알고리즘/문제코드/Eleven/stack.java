package 알고리즘.문제코드.Eleven;

public class stack {


    public static void main(String[] args,  int[] A) {

        boolean[] check = new boolean[2000002];
        int result = 0;

        for (int i : A) {
            int index = i + 1000000;

            if(!check[index]){
                check[index] = true;
                result += 1;
            }else{
                check[index] = false;
                result -= 1;
            }
        }



    }
}
