package 자바공부.자료구조.콜렉션;

import java.util.HashMap;
import java.util.Map;

public class Ex_Map_HashMap {
    public static void main(String[] args) {

        // n 1. 초기화
        HashMap<Integer, String> map1 = new HashMap<>();
        @SuppressWarnings("serial")
		HashMap<Integer, String> map2 = new HashMap<Integer, String>(){{
            put(1, "b");
        }};


        // n 2. 값 추가, 값 제거
        map1.put(1, "사과");
        map1.put(1, "사과2");
        map1.put(2, "바나나");
        map1.put(3, "포도");
        System.out.println(map1); // {1=사과2, 2=바나나, 3=포도}

        map1.remove(1);
        map1.clear();
        System.out.println();


        // n 3. 값 출력
        map1.put(1, "사과");
        map1.put(2, "바나나");
        map1.put(3, "포도");

        System.out.println(map1); // {1=사과, 2=바나나, 3=포도}
        System.out.println(map1.get(1)); // 사과

        for (Map.Entry<Integer, String> entry : map1.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
            /*
            1 사과
            2 바나나
            3 포도
            */
        }

    }
}
