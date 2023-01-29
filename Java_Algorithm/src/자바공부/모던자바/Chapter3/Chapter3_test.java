package 자바공부.모던자바.Chapter3;

import 자바공부.모던자바.Apple;

import java.awt.*;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Chapter3_test {
    public static void main(String[] args) {

        Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
        // 뒤집기
        c.reversed();
        // 만약 같다면 한번더 정렬함
        c.thenComparing((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));

        Predicate<Apple> redApple = (Apple a) -> a.getColor() == Color.RED;
        Predicate<Apple> p = redApple.negate(); // 빨강이 아닌
        p = redApple.and(a -> a.getWeight() > 150); // 빨강이고 150보다 큰
        p = redApple.negate().or((a) -> a.getWeight() > 150); // 빨강이 아니거나, 150보다 큰


        Function<Integer, Integer> pp = x -> x + 1;
        Function<Integer, Integer> m = x -> x * 2;
        Function<Integer, Integer> pm = pp.andThen(m);
        Function<Integer, Integer> pm2 = pp.compose(m);

        // 12
        System.out.println(pm.apply(5));
        // 11
        System.out.println(pm2.apply(5));


    }
}
