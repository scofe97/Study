package 자바공부.자료구조.콜렉션;

import java.util.Map;
import java.util.TreeMap;

public class EX_Map_TreeMap {
    public static void main(String[] args) {

        // n 1. 초기화
        TreeMap<Integer, String> map1 = new TreeMap<>();
        
        @SuppressWarnings("serial")
		TreeMap<Integer, String> map2 = new TreeMap<Integer, String>(){{
            put(1, "a");
        }};


        // n 2. 값 추가, 값 삭제
        map1.put(1, "사과");
        map1.put(1, "사과2");
        map1.put(2, "바나나");
        map1.put(3, "포도");

        map1.remove(1);
        map1.clear();


        // n 3. 값 출력
        map1.put(1, "사과");
        map1.put(2, "바나나");
        map1.put(3, "포도");

        System.out.println(map1); // {1=사과, 2=바나나, 3=포도}
        System.out.println(map1.get(1)); // 사과
        System.out.println(map1.firstEntry()); // 1=사과
        System.out.println(map1.firstKey()); // 1
        System.out.println(map1.lastEntry()); // 3=포도
        System.out.println(map1.lastKey()); // 3

        for (Map.Entry<Integer, String> entry : map1.entrySet()) {
            System.out.println(entry);
            /*
            1=사과
            2=바나나
            3=포도
            */
        }

        // n 4. entry 제어
        for (Map.Entry<Integer, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.setValue("응애"));
            System.out.println(entry.getValue());
            /*
            1
            사과
            응애
            2
            바나나
            응애
            3
            포도
            응애
            */
        }


    }
}
