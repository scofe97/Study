package 알고리즘.문제코드.SSAFY.수업_220819;

public class Problem_recursive {
    public static void main(String[] args) {
        // 120
        System.out.println(recursive(5));

        System.out.println(fibo(5));
    }

    static int recursive(int n){
        if(n == 1){
            return 1;
        }else{
            return n * recursive(n-1);
        }
    }

    static int fibo(int n){
        if(n > 2){
            return fibo(n-1) + fibo(n-2);
        }else{
            return n;
        }

    }
}
