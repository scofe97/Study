package 자바공부.기본개념.Reflection;

public class Child extends Parent {
    public String cstr1 = "1";
    private String cstr2 = "2";

    public Child() {
    }

    private Child(String str) {
        cstr1 = str;
    }

    public int method4(int n) {
        System.out.println("method4: " + n);
        return n;
    }

    private int method5(int n, int t) {
        System.out.println("method5: " + n + t);
        return n+t;
    }
}
