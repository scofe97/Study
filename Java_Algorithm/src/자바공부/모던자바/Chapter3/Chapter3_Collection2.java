package 자바공부.모던자바.Chapter3;

import java.util.HashMap;
import java.util.Map;

public class Chapter3_Collection2 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("김싸피", 24);
        map.put("이싸피", 25);
        map.put("박싸피", 26);
        map.put("정싸피", 30);

        // void forEach(BiConsumer<K,V>)
        map.forEach((key, value) -> System.out.println(key + " : " + value));

        // void replaceAll(BiFunction<K,V,V>)
        map.replaceAll((key, value) -> value + 1);

        // V compute(K key, BiFunction<K,V,V> f) : 지정된 키의 값에 f를 수행
        int age = map.compute("김싸피", (key, value) -> value + 10);
        System.out.println(age);

        // V computeIbAbsent (K key, Function<K,V> f) : 키가 없으면 f 수행후 추가
        age = map.computeIfAbsent("홍길동", (key) -> 1); // 홍길동 없으면 1살로 추가
        System.out.println(age);

        // V computeIfPresent(K key, BiFunction<K, V, V> f) : 지정된 키가 있을때 작업 f를 수행
        age = map.computeIfPresent("홍길동", (key, value) -> value + 20); // 홍길동이 존재하면 20추가
        System.out.println(map);


        // V merge(K key, V value, BiFunction<V, V, V> f) : 모든 요소에 병합작업 f를 수행
        map.merge("홍길동", 10, (original, newValue) -> original + newValue);


        System.out.println(age);
        System.out.println(map);

    }
}
