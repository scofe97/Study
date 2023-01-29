package 자바공부.자료구조.콜렉션;

import java.util.Arrays;
import java.util.TreeSet;

public class EX_Set_TreeSet {

    public static void main(String[] args) {

        // n 1. 초기화
        TreeSet<Integer> set1 = new TreeSet<>();
        TreeSet<Integer> set2 = new TreeSet<>(set1);
        TreeSet<Integer> set3 = new TreeSet<>(Arrays.asList(1,2,3));


        // n 2. 추가, 삭제, 출력, 크기기
        set1.add(1);
        set2.add(2);
        set3.add(3);
        System.out.println(set1);

        set1.remove(3);
        set1.clear();
        System.out.println(set1);

        set1.add(5);
        set1.add(2);
        set1.add(3);
        set1.add(4);
        set1.add(1);
        System.out.println(set1); // [1, 2, 3, 4, 5]
        System.out.println(set1.first());
        System.out.println(set1.last());
        System.out.println(set1.higher(3));
        System.out.println(set1.lower(3));

        System.out.println(set1.size());

        for (Integer integer : set1) {
            System.out.println(integer);
        }






    }
}
