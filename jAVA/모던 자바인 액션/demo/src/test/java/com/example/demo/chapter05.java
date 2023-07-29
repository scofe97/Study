package com.example.demo;

import com.example.demo.object.Dish;
import com.example.demo.object.Trader;
import com.example.demo.object.Transaction;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;

public class chapter05 {

//    public static final List<Dish> menu = asList(
//            new Dish("pork", false, 800, Dish.Type.MEAT),
//            new Dish("beef", false, 700, Dish.Type.MEAT),
//            new Dish("chicken", false, 400, Dish.Type.MEAT),
//            new Dish("french fries", true, 530, Dish.Type.OTHER),
//            new Dish("rice", true, 350, Dish.Type.OTHER),
//            new Dish("season fruit", true, 120, Dish.Type.OTHER),
//            new Dish("pizza", true, 550, Dish.Type.OTHER),
//            new Dish("prawns", false, 400, Dish.Type.FISH),
//            new Dish("salmon", false, 450, Dish.Type.FISH)
//    );

    @Test
    void test01() {
        // 기존 방식
        List<Dish> vegetarianDishes = new ArrayList<>();
        for (Dish dish : Dish.menu) {
            if (dish.isVegetarian()) vegetarianDishes.add(dish);
        }

        // 자바 8이후
        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .forEach(System.out::println);
    }

    @Test
    void filtering() {
        // 고유 요소 필터링
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    void Slicing() {
        // TAKEWHILE
        System.out.println("TAKEWHILE");
        Dish.menu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .forEach(System.out::println);

        // DropWhile
        System.out.println("DROPWHILE");
        Dish.menu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .forEach(System.out::println);

        // 스트림 축소
        System.out.println("LIMIT");
        Dish.menu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .limit(3)
                .forEach(System.out::println);

        // 요소 건너뛰기
        System.out.println("SKIP");
        Dish.menu.stream()
                .filter(d -> d.getCalories() < 320)
                .skip(2)
                .forEach(System.out::println);
    }

    @Test
    void mapping() {

        // 스트림의 각 요소에 함수 적용하기
        List<String> dishNames = Dish.menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        // map
        List<String> words = asList("Modern", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        // 문자열 map 적용1
        words.stream()
                .map(word -> word.split(""))
                .distinct()
                .forEach(System.out::println);

        // 문자열 map 적용2
        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream) // 각 배열을 별도의 스트림으로 생성
                .distinct()
                .forEach(System.out::println);

        // 문자열 flatMap 적용
        words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // 하나의 스트림으로 반환
                .distinct()
                .forEach(System.out::println);

        List<Integer> answer = Stream.of(1, 2, 3, 4, 5)
                .map(i -> i * i)
                .collect(Collectors.toList());

        List<Integer> list1 = asList(1, 2, 3);
        List<Integer> list2 = asList(3, 4);
        List<int[]> pairs = list1.stream()
                .flatMap(i -> list2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
    }

    @Test
    void searchAndMattching() {

        // anyMatch
        if (Dish.menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("메뉴에 채식주의자를 위한 음식이 있다.");
        }

        // allMatch
        if (Dish.menu.stream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("메뉴의 음식은 모두 건강식이다.");
        }

        // noneMatch
        if (Dish.menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("메뉴의 음식은 모두 건강식이다.");
        }

        // 위 3가지요소는 쇼트서킷 평가를 진행한다 ( && || 연산중 하나라도 만족하면 뒤는 계산하지 않음)


        // findAny
        Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println("dish = " + dish.getName()));

        // findFirst
        Stream.of(1, 2, 3, 4, 5)
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst()
                .ifPresent(System.out::println);

    }

    @Test
    void reducing() {
        // 스트림 요소 조합으로 질의 표현
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);

        // 요소의 합
        int sum = 0;
        for (Integer x : numbers) {
            sum += x;
        }

        // reduce는 초기값과, 두 요소를 조합해 새로운 값을 만드는 함수
        sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("product = " + product);

        // 초기값이 없는 경우 null로 반환(초깃값없이 연산이안됨) 그렇기에 Optional로 반환됨
        Optional<Integer> sum2 = numbers.stream().reduce((a, b) -> a + b);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);

        Integer count = Dish.menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
    }

    @Test
    void practice() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 2011년에 일어난 모든 트랜잭션 오름차순 정렬
        List<Transaction> collect = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());
        for (Transaction transaction : collect) {
            System.out.println("transaction = " + transaction);
        }

        // 거래자가 근무하는 모든 도시를 중복없이 나열
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        // 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순 정렬
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);

        // 모든 거래자의 이름을 알파벳 순으로 정렬해서 반환
        String reduce = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted(String::compareTo)
                .reduce("", (a, b) -> a + " " + b);
        System.out.println("reduce = " + reduce);

        // 밀리노에 거래자가 있는가?
        boolean milan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(t -> t.getCity().equals("Milan"));
        System.out.println("milan = " + milan);

        // 케임브리지에 거주하는 거래자의 모든 트랜잭션의 값
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 전체 트랙잭션중 최대값
        Integer max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);

        // 전체 트랜잭션중 최소값
        Integer min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::min);
    }
}
