package 자바공부.자료구조.콜렉션;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Consumer;

public class EX_List_ArrayList {
    public static void main(String[] args) {

        // n  1.기존배열로 ArrayList 만들기
        String[] data = {"138", "129", "142"};
        ArrayList<String> pitches = new ArrayList<>(Arrays.asList(data));
        System.out.println(pitches);


        // n  2.ArrayList 기본함수 ( get, size, contains, remove )
        pitches.add("129");
        System.out.println(pitches);
        System.out.println(pitches.get(1));
        System.out.println(pitches.size());
        pitches.remove("129");
        System.out.println(pitches.contains("129"));
        System.out.println(pitches);
        System.out.println(pitches.remove(0));
        pitches.add("138");


        // n  3.join 함수로 배열 한번에 출력
       String result = String.join(",", pitches);
       System.out.println(result);


        // n  4.정렬 ( Collections.sort(배열이름) )
        Collections.sort(pitches);
        System.out.println(pitches);


        // n  5.all 관련 함수
        //  addAll(list) 함수 -> list의 내용을 모두 추가
        //  removeAll() 함수 -> list  내용과 겹치면 모두 제거
        //  removeIf() 함수 -> 조건에 맞는 값만 제거함
        //  retainAll() 함수 -> 겹치는 부분만 남긴다
        pitches = new ArrayList<>(Arrays.asList(data));

        ArrayList<String> pitches2 = new ArrayList<>();
        pitches2.add("응");
        pitches2.addAll(pitches);
        System.out.println(pitches2);

        pitches2.removeAll(pitches);
        System.out.println(pitches2);

        pitches2.add("애");
        pitches2.removeIf(n -> (Objects.equals(n, "응")));
        System.out.println(pitches2); // [애]

        pitches2.addAll(pitches);
        pitches2.retainAll(pitches);
        System.out.println(pitches2); // [129, 138, 142]
        pitches2.removeIf(n -> (Objects.equals(n, "142")));
        System.out.println(pitches2); // [129, 138]
        pitches2.clear();
        System.out.println(pitches2);


        // n  6.forEach(Consumer) -> 모든 요소를 순회하면서 Consumer로 전달
        int[] data2 = {2, 4, 6};
        ArrayList<Integer> pitches3 = new ArrayList<>(Arrays.asList(2,4,6));

        Consumer<String> lamda = item -> System.out.println("item : " + item);
        pitches2.forEach(lamda);
    }
}
