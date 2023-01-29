package 자바공부.모던자바.Chapter5;

import 자바공부.모던자바.Apple;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Chapter5_Stream {
    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(Color.red, 100));
        inventory.add(new Apple(Color.red, 200));
        inventory.add(new Apple(Color.red, 300));
        inventory.add(new Apple(Color.red, 400));
        inventory.add(new Apple(Color.red, 500));
        inventory.add(new Apple(Color.green, 50));
        inventory.add(new Apple(Color.green, 100));
        inventory.add(new Apple(Color.green, 150));
        inventory.add(new Apple(Color.green, 200));
        inventory.add(new Apple(Color.green, 250));

        List<Apple> red = inventory.stream()
                .filter(a -> a.getColor() == Color.red)
                .limit(3)
                .collect(toList());

        System.out.println(red);

        List<Apple> red2 = inventory.stream()
                .filter(a -> a.getColor() == Color.red)
                .skip(2)
                .collect(toList());

        System.out.println(red2);


        List<Integer> map1 = inventory.stream()
                .map(a -> a.getWeight())
                .collect(Collectors.toList());
        System.out.println(map1);

        List<String> test = Arrays.asList("Hello", "World");
        List<String> flatMap = test.stream()
                .map(word -> word.split(""))
                .flatMap(word -> Arrays.stream(word))
                .collect(Collectors.toList());
        System.out.println(flatMap);

    }
}
