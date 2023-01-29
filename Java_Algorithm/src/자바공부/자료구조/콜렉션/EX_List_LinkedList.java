package 자바공부.자료구조.콜렉션;

import java.util.Arrays;
import java.util.LinkedList;

public class EX_List_LinkedList {
    public static void main(String[] args) {

        // n  1. LinkedList 선언
        LinkedList<Integer> list1= new LinkedList<>(Arrays.asList(1,2,3));
        LinkedList<String> list2= new LinkedList<>(Arrays.asList("1","2","3"));
        System.out.println(list1); // [1, 2, 3]
        System.out.println(list2); // [1, 2, 3]


        // n  2. 값 추가, 값 제거
        list1.add(4);
        list1.add(4, 5);
        System.out.println(list1);

        list1.removeFirst(); // [2,3,4,5]
        list1.removeLast(); // [2,3,4]
        list1.remove(); // [3,4]
        list1.remove(1); // [3]
        list1.clear(); // []


        // n 3. 크기 구하기, 값 출력
        System.out.println(list2.size()); // 3

        for (String s : list2) {
            System.out.println(s);
            /*
            * 1
            * 2
            * 3
            */
        }


        // n  4. 값 검색
        LinkedList<Integer> list = new LinkedList<Integer>(Arrays.asList(1,2,3));
        System.out.println(list.contains(1)); //list 에 1이 있는지 검색 : true
        System.out.println(list.indexOf(1)); //0
        System.out.println(list.indexOf(5)); //-1
    }
}
