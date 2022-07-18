package 자바공부.기본문법;

public class Static {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();

        Singleton singleton = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton == singleton2);
    }
}

class Singleton{
    private static Singleton one;
    private Singleton() {

    }

    public static Singleton getInstance(){
        if(one == null){
            one = new Singleton();
        }
        return one;
    }
}


class Counter{
    static int count = 0;
    Counter(){
        count++;
        System.out.println(count);
    }

    public static int getCount(){
        return count;
    }
}