package 자바공부.스트림;

import java.util.*;

public class EX_Stream5_list_sort {

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

        @Override
        public boolean equals(Object o) {
            return false;
        }
    }

    public static void main(String[] args) {

        // chapter 1. Collecionts.sort()로 List 정렬
        List<String> list = new ArrayList<>(Arrays.asList("Kiwi", "apple", "Melon", "orange", "banana"));

        // note : sort
        Collections.sort(list);
        System.out.println(list); // [Kiwi, Melon, apple, banana, orange]

        // note : sort in reverse order
        Collections.sort(list, Collections.reverseOrder());
        System.out.println(list); // [orange, banana, apple, Melon, Kiwi]


        // chapter 1-1 대소문자 없이 비교하여 정렬
        // note : ( 정렬한 리스트,  정렬 방법 ( Comparator 함수 )
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        System.out.println(list);// [apple, banana, Kiwi, Melon, orange]


        // chapter 2. List.sort()로 정렬
        // note : Comparator 함수로 정렬한다
        //  sort(Comparator.naturalOrder()) : 오름차순 정렬
        //  sort(Comparator.reverseOrder()) : 내림차순 정렬
        //  sort(String.CASE_INSENSITIVE_ORDER) : 대소문자 없이 비교정렬

        list.sort(Comparator.naturalOrder());
        System.out.println(list); // [Kiwi, Melon, apple, banana, orange]

        list.sort(Comparator.reverseOrder());
        System.out.println(list); // [orange, banana, apple, Melon, Kiwi]

        list.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(list); // [apple, banana, Kiwi, Melon, orange]


        // chapter 3. List.sort()로 List 정렬 : Comparator 직접구현
        // note : Comparator 클래스를 직접 구현해서 사용해도됨 ( compare() 함수 오버라이딩 )
        //  o1과 o2를 비교하여서 o1이 크다 (0보다 큰값),
        //  o1과 o2를 비교하여서 o2가 크다 (0보다 작은값)
        list.sort(new MyComparator());
        System.out.println(list); // [apple, banana, Kiwi, Melon, orange]

        // note : 익명클래스 경우
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2); // [apple, banana, Kiwi, Melon, orange]
            }
        });

        // note : 람다로 구현 ( 메소드 레퍼런스 타입 )
        list.sort(String::compareToIgnoreCase);
        System.out.println(list);
    }
}
