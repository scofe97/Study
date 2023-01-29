package 자바공부.자료구조.콜렉션;

import java.util.Arrays;
import java.util.Vector;
import java.util.function.Consumer;

public class EX_List_Vector {

    public static void main(String[] args) {
        // n 1. 초기화
        Vector<Integer> v1 = new Vector<>();
        Vector<Integer> v2 = new Vector<>(1);
        Vector<String> v3 = new Vector<>(Arrays.asList("1","2","3"));


        // n 2. 값 추가, 삭제, 출력
        v1.add(1);
        v1.add(2);
        v1.add(null);
        System.out.println(v1);

        v1.add(1, 10); // [1, 2, null, 1]
        v1.remove(1);
        System.out.println(v1); // [1, 2, null]

        v1.removeAllElements();
        v1.clear();
        System.out.println(v1);

        v1.add(1);
        v1.add(2);
        v1.add(3);
        Consumer<Integer> lamda = System.out::println;
        v1.forEach(lamda);


        // n 3. 크기구하기
        System.out.println(v1.size());
    }
}
