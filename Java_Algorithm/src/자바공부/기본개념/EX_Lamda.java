package 자바공부.기본개념;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@FunctionalInterface
interface MyLambdaFunction {
    int max(int a, int b);
}

public class EX_Lamda {
    public static void main(String[] args) {

        // 기존의 익명함수
        System.out.println(new MyLambdaFunction() {
            public int max(int a, int b) {
                return a > b ? a : b;
            }
        }.max(3, 5));


        MyLambdaFunction lamdaFunction = (int a, int b) -> Math.max(a, b);
        System.out.println(lamdaFunction.max(3, 5));


        // chapter 1. Supplier <T>
        // note 매개변수 없이 반환값 만을 갖는 함수형 인터페이스
        Supplier<String> supplier = () -> "Hello World";
        System.out.println(supplier.get()); // Hello World


        // chapter 2. Consumer <T>
        // note 객체 T를 매개변수로 사용하며, 반환값은 없는 인터페이스
        //  accept(T t) 추상메소드 가지며, andThen으로 하나의 함수가 끝난 후
        //  다음 Consumer를 연쇄적으로 사용

        // hint :: (메소드 참조) -> d -> System.out.println(d) = System.out::println
       Consumer<String> consumer = (str) -> System.out.println(str.split(" ")[0]);
        consumer.andThen(System.out::println).accept("1 2 3"); // 1 enter 1 2 3
        consumer.andThen(System.out::println);
        consumer.accept("1 2 3"); // 1


        // chapter 3. Function <T, R>
        // note Function은 객체 T를 매개변수로 받아서 처리한 후 R로 반환
        Function<String, String> function = (str) -> str + " Hello World";
        System.out.println(function.apply("bo")); // bo Hello World


        // chapter 4. Predicate <T>
        // note 객체 T를 매개변수로 받아 처리한 후 Boolean을 반환한다
        //  Boolean test(T t)를 추상메소드로 가짐
        Predicate<String> predicate = (str) -> str.equals("Hello");
        System.out.println(predicate.test("Hello1")); // false
        System.out.println(predicate.test("Hello")); // true
    }
}
