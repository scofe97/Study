package 자바공부.자료구조.콜렉션;

import java.util.Arrays;
import java.util.HashSet;

public class EX_Set_HashSet {

    public static void main(String[] args) {

        // n 1. 초기화
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>(set1);
        HashSet<Integer> set3 = new HashSet<>(10);
        HashSet<Integer> set4 = new HashSet<>(10, 0.7f);
        HashSet<Integer> set5 = new HashSet<>(Arrays.asList(1,2,3));


        // n 2. 추가, 삭제, 크기, 출력
        set1.add(1);
        set1.add(2);
        set1.add(3);
        System.out.println(set1); // [1, 2, 3]

        set1.remove(1);
        set1.clear();
        System.out.println(set1);

        System.out.println(set1.size());

        set1.add(1);
        set1.add(2);
        set1.add(3);
        for (Integer a : set1) {
            System.out.println(a);
        }


        // n 3. 검색
        System.out.println(set1.contains(1));
        System.out.println(set1.contains(4));

    }
}
