package 자바공부.스트림;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EX_Stream3_function {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("김1","심", "김1", "최","유", "심", "김2");

        // chapter 1. distinct()
        names.stream()
                .distinct() // 중복제거
                .forEach(s -> System.out.print(s + " "));

        System.out.println();

        // chapter 2. filter()
        names.stream()
                .filter(n -> n.startsWith("김")) // 시작이 김씨
                .forEach(s -> System.out.print(s + " "));

        System.out.println();

        names.stream()
                .distinct() // 중복제거
                .filter(n -> n.startsWith("김")) // 시작이 김씨
                .forEach(System.out::println);


        // chapter 3. flatMapToInt
        // note 스트림 평면화
        //  중복된 스트림을 1차원으로 평면화 시키는 것
        //  이처럼 flatMap을 사용하면 중복 구조로 되어있는 리스트를 하나의 스트림처럼 다룰 수 있습니다.
        List<String> inputList1 = Arrays.asList("java8 lamda", "stream mapping");
        inputList1.stream()
                .flatMap(data -> Arrays.stream(data.split(" "))) // 배열을 스트림으로 변환
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        List<String> inputList2 = Arrays.asList("10, 20, 30", "40, 50, 60");
        inputList2.stream()
                .flatMapToInt(data -> {
                    String[] strArr = data.split(",");
                    int[] intArr = new int[strArr.length];
                    for (int i = 0; i < strArr.length; i++) {
                        intArr[i] = Integer.parseInt(strArr[i].trim()); // 숫자를 뽑아 숮자 스트림으로 재생성
                    }
                    return Arrays.stream(intArr);
                })
                .forEach(s -> System.out.print(s + " "));

        System.out.println();

        // chapter 4. mapToInt() 요소를 대체하는 방법
        List<Worker> workerList = Arrays.asList(
                new Worker("강성현", 30),
                new Worker("깡냉", 20),
                new Worker("까랭", 10)
        );
        workerList.stream()
                .mapToInt(Worker::getAge) // IntStream을 반환
                .forEach(s -> System.out.print(s + " "));
        System.out.println();

        // chapter 5. asDoubleStream() -> double Stream으로 변경
        //  boxed() -> double Stream으로 변경
        int[] intArray = {1, 2, 3, 4, 5};

        IntStream intStream = Arrays.stream(intArray);
        intStream.asDoubleStream() // double stream 생성
                .forEach(s -> System.out.print(s + " "));
        System.out.println();


        // chapter 6. sorted
        Stream<Integer> intStream2 = Arrays.stream(new Integer[] {5, 3, 1, 2, 4});

        intStream2
                .sorted(Integer::compareTo) // 오름차순으로 정렬 기본 Comparable을 따른다.
                .forEach(s -> System.out.print(s + ","));
        System.out.println();

        List<Worker> workerList2 = Arrays.asList(
                new Worker("강성현", 30),
                new Worker("깡냉", 20),
                new Worker("까랭", 10));

        workerList2.stream()
                .sorted()
                .forEach(s -> System.out.print(s.getAge() + ","));
        System.out.println();

        // chapter 7. peek() -> 중간처리, foreach() -> 최종처리
        ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        int[] intArr = {1, 2, 3, 4, 5};
        intList.stream()
                .filter(a -> a%2==0)
                .peek(System.out::println); // 최종처리 안해주면 동작안함

        // forEach는 최종처리 메소드(Terminal) 이므로 아래는 정상 동작한다.
        Arrays.stream(intArr)
                .filter(a -> a%2 == 0)
                .forEach(System.out::println);
    }


}
