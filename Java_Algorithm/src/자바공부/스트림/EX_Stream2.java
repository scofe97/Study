package 자바공부.스트림;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EX_Stream2 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3);
        Stream<Integer> stream = list.stream();

        Integer[] intArray = {1,2,3};
        Stream<Integer> stream2 = Arrays.stream(intArray);
        int[] intArray2 = {1,2,3,4};
        IntStream intStream = Arrays.stream(intArray2);

        IntStream stream3 = IntStream.rangeClosed(1, 100);
        IntStream stream4 = IntStream.range(1, 100);
        stream3.forEach(System.out::println);
        stream4.forEach(System.out::println);

        List<Member> list2 = Arrays.asList(
                new Member("깡냉", Member.MALE, 30),
                new Member("여자깡냉", Member.FEMALE, 20),
                new Member("까랭", Member.MALE, 34),
                new Member("여자까랭", Member.FEMALE, 25)
        );

        double ageAvg = list2.stream()                 // 오리지날 스트림
                .filter(m -> m.getSex() == Member.MALE)   // 중간 처리 스트림
                .mapToInt(Member :: getAge)               // 중간 처리 스트림
                .average()                                // 최종 스트림
                .getAsDouble();

        // 위의 코드는 사실 아래와 같다.
        // Stream<Member> maleFemaleStream = list.stream();
        // Stream<Member> maleStream = maleFemaleStream.filter(m -> m.getSex() == Member.MALE);
        // IntStream ageStream = maleStream.mapToInt(Member::getAge);
        // OptionalDouble optionalDouble = ageStream.average();
        // double ageAvg = optionalDouble.getAsDouble();

        System.out.println("남자 평균 나이 : " + ageAvg);
    }
}