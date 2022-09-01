package 자바공부.모던자바.Chapter6;

import 자바공부.모던자바.Dish;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Chapter6_2 {

    public static void main(String[] args) throws IOException {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );


        // chapter 6.4 분할
        // note 분할 함수라 불리는 프레디케이트를 분류 함수로 사용하는 특수한 그룹이다.
        //  분할 함수는 불리언을 반환하므로 맵의 키 형식은 Boolean이며, 참 또는 거짓을 갖는 2개의 그룹으로 분류된다.

        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
                .collect(Collectors
                        .partitioningBy(dish -> dish.isVegetarian()));

        System.out.println(partitionedMenu);
        // {false=[pork, beef, chicken, prawns, salmon], true=[french fries, rice, season fruit, pizza]}


        // 분할방식과 프레디케이트 방식식
        List<Dish> vegetarianDishes = partitionedMenu.get(true);
        vegetarianDishes = menu.stream().filter(dish -> dish.isVegetarian()).collect(Collectors.toList());


        // chapter 6.4.1 분할의 장점
        Map<Boolean, Map<Dish.Type, List<Dish>>> partitionedMenu2 = menu.stream()
                .collect(Collectors.partitioningBy(
                        dish -> dish.isVegetarian(),
                        Collectors.groupingBy(dish -> dish.getType())));
        System.out.println(partitionedMenu2);

        Map<Boolean, Dish> partitionedMenu3 = menu.stream()
                .collect(Collectors.partitioningBy(
                        dish -> dish.isVegetarian(),
                        Collectors.collectingAndThen(Collectors.maxBy(
                                        Comparator.comparingInt(dish -> dish.getCalories())),
                                o -> o.get())));
        System.out.println(partitionedMenu3);


        // chapter 6.4.2 숫자를 소수와 비소수로 분할하기
        System.out.println(partitionPrimes(10));


    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = ((int) Math.sqrt((double) candidate));
        return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
        //스트림의 모든 정수로 candidate를 나눌 수 없으면 참을 반환
    }

    public static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n)
                .boxed()
                .collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }

}
