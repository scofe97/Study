package 자바공부.스트림;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class EX_Stream4_function {
    public static void main(String[] args) {

        int[] intArr = {1,2,3,4,5};

        long count = Arrays.stream(intArr)
                .filter(n -> n%2 == 0)
                .count();
        System.out.println(count);


        long sum = Arrays.stream(intArr)
                .filter(n -> n%2 == 0)
                .sum();
        System.out.println(sum);


        double avg = Arrays.stream(intArr)
                .filter(n -> n%2 == 0)
                .average()
                .getAsDouble();
        System.out.println(avg);


        double max = Arrays.stream(intArr)
                .filter(n -> n%2 == 0)
                .max()
                .getAsInt();
        System.out.println(max);


        double min = Arrays.stream(intArr)
                .filter(n -> n%2 == 0)
                .min()
                .getAsInt();
        System.out.println(min);


        double first = Arrays.stream(intArr)
                .filter(n -> n%2 == 0)
                .findFirst()
                .getAsInt();
        System.out.println(first);


        List<Integer> list = new ArrayList<>();
        OptionalDouble optional = list.stream()
                .mapToInt(Integer::intValue)
                .average();
        if(optional.isPresent()){
            System.out.println("평균 : " + optional.getAsDouble());
        }else{
            System.out.println("xxx");
        }

        double avg2 = list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
        System.out.println("평균 : " + avg2);


        list.stream()
                .mapToInt(Integer::intValue)
                .average()
                .ifPresent(data -> System.out.println("평균 : " + data));

    }
}
