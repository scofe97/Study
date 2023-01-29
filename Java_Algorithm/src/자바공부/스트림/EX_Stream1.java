package 자바공부.스트림;

import java.util.*;
import java.util.stream.Stream;

public class EX_Stream1 {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        // chapter Iterator
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if (Objects.equals(value, "b")) {
                System.out.println("값 : " + value);
            }
        }


        // chapter Stream
        for (String value : list) {
            if (Objects.equals(value, "b")) {
                System.out.println("값 : " + value);
            }
        }


        // chapter Stream filter
        list.stream().filter("b"::equals).forEach(System.out::println);


        // chapter 1. 스트림 생성
        Stream<String> stream1 = list.stream();

        String[] array = new String[]{"a", "b", "c"};
        Stream<String> stream2 = Arrays.stream(array);


        List<Worker> list2 = Arrays.asList(
                new Worker("강성현", 30),
                new Worker("깡냉", 20)
        );

        Stream<Worker> stream3 = list2.stream();
        stream3.forEach(s -> {
            String name = s.getName();
            int age = s.getAge();
            System.out.println(name + " - " + age);
        });


        List<String> list3 = Arrays.asList("A군", "B군", "C군", "D군", "E군", "F군");

        // 순차처리
        Stream<String> stream = list.stream();
        stream.forEach(System.out::println);

        System.out.println();

        // 병렬처리
        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach((str) -> System.out.println(str + " : " + Thread.currentThread().getName()));


        List<Worker> workerList = Arrays.asList(
                new Worker("깡냉", 30),
                new Worker("가뤵", 20),
                new Worker("곡냉", 10)
        );

        double avg = workerList.stream()
                .mapToInt(Worker::getAge) // 중간 처리 (age로 매핑)
                .average() // 최종 처리 (평균)
                .getAsDouble();

        System.out.println("평균점수 : " + avg);
    }
}

