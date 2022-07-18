package 자바공부.자료구조.콜렉션;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EX_LIst_to_Set {

    public static void main(String[] args) {
        // n 1. List를 Set으로 변환
        List<Integer> sourceList = Arrays.asList(0,1,2,3,4,5);
        Set<Integer> targetSet = new HashSet<>(sourceList);


        // n 2. Set을 LIst로 변환
        Set<Integer> sourceSEt = new HashSet<>(Arrays.asList(0,1,2,3,4,5));
        List<Integer> targetList = new ArrayList<>(sourceSEt);


        // chapter 3. Steram의 아이템들을 HashSet으로 리턴
        // note -> collect(Supplier supplier, BiConsumer accumulator, BiConsumer combiner)

        Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        HashSet<String> fruitHashSet = fruits.collect(HashSet::new, HashSet::add, HashSet::addAll);
        fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        Set<String> fruitHashSet2 = fruits.collect(Collectors.toSet());
        fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        List<String> fruitList = fruits.collect(Collectors.toList());

        for (String s : fruitHashSet) {
            System.out.println(s);
        }

        // chapter 4. Stream 아이템을 1개의 String객체로 만들기
        fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        String result2 = fruits.collect(Collectors.joining());
        System.out.println(result2);

        fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        String result3 = fruits.collect(Collectors.joining(", "));
        System.out.println(result3);

        fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
        Function<String, Integer> getCount = fruit -> fruit.length();
        Optional<String> result = fruits.max(new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                return s1.length() - s2.length();
            }
        });
        System.out.println(result.orElse("no item"));


    }

}
