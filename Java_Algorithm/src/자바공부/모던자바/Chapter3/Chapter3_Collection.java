package 자바공부.모던자바.Chapter3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Chapter3_Collection {

    public static void main(String[] args) {
        Collect test1 = new Collect(10, 20);
        Collect test2 = new Collect(20, 40);
        Collect test3 = new Collect(30, 60);

        List<Collect> list = new ArrayList<>();
        list.add(test3);
        list.add(test2);
        list.add(test1);

        Collections.sort(list);

        for (Collect collect : list) {
            System.out.println(collect);
        }

        list.sort(((o1, o2) -> o2.x - o1.x));
        for (Collect collect : list) {
            System.out.println(collect);
        }

        List<String> list2 = new ArrayList<>();
        list2.add("김싸피");
        list2.add("이싸피");
        list2.add("박싸피");
        list2.add("정싸피");

        // Consumer
        list2.forEach(name -> System.out.print(name + " "));
        System.out.println();
        // Predicate
        list2.removeIf(name -> name.contains("이") || name.contains("박"));
        System.out.println(list2);
        // UnaryOperator
        list2.replaceAll(name -> name + "교육생");
        System.out.println(list2);

    }

    static class Collect implements Comparable<Collect>, Comparator<Collect> {
        int x;
        int y;

        Collect(int x, int y){
            this.x  = x;
            this.y = y;
        }


        @Override
        public int compareTo(Collect o) {
            return this.x - o.x;
        }

        @Override
        public int compare(Collect o1, Collect o2) {
            return o1.x - o2.x;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}
