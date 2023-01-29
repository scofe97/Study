package 자바공부.기본개념;

public class Lambda {
    public static void main(String[] args) {
        MyCalculator mc = new MyCalculator();
        int result = mc.sum(3,4);
        System.out.println(result);

        // 메서드가 2개이상이면 사용불가능
       Calculator mc2 = (int a, int b) -> a+b;
        int result2 = mc.sum(3,4);
        System.out.println(result2);


    }
}

interface Calculator {
    int sum(int a, int b);
}

class MyCalculator implements Calculator {
    public int sum(int a, int b) {
        return a + b;
    }
}
