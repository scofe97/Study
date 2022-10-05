package 자바공부.기본문법;

public class Ex_Var {
    
    // var a = 1;  멤버 변수에서는 사용불가
    
    public static void main(String[] args) {
        // var string = "Hello World"; // 자동으로 String 맞춤
        // var list = new ArrayList<String>(); // 자동으로 ArrayList<String> 으로 맞춤

        int[] arr = {1,2,3};
        for (int i : arr) {
            System.out.println("i : " + i);
        }
    }
}
