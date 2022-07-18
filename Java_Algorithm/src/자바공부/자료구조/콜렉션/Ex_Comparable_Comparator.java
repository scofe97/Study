package 자바공부.자료구조.콜렉션;

import java.util.Comparator;

public class Ex_Comparable_Comparator {
    public static void main(String[] args) {

        // chapter 1. Comparable<T> 인터페이스
        // note 객체를 정렬하는데 사용하는 메소드인 compareTo(T o) 를 정의하고 있음
        //  보통 객체의 정렬에서 사용됨

        // note 객체의 비교는 어떻게 진행되는가?
        Student a = new Student(17, 2);	// 17살 2반
        Student b = new Student(28, 1);	// 18살 1반

        int isBig = a.compareTo(b);

        if(isBig > 0){
            System.out.println("크다");
        } else if (isBig < 0) {
            System.out.println("작다");
        } else {
            System.out.println("같다");
        }

        // chapter 2. Comparator
        // note Comparator은 클래스 자체가 정렬 기준으로 사용된다
        //  외부 정렬기준을 정의한다
        Student2 a2 = new Student2(17, 2);
        Student2 b2 = new Student2(28, 5);
        Student2 c2 = new Student2(29, 3);

        int isBig2 = a2.compare(b2,c2);

        if(isBig2 > 0){
            System.out.println("크다");
        } else if (isBig2 < 0) {
            System.out.println("작다");
        } else {
            System.out.println("같다");
        }

        int isBig3 = comp.compare(b2,c2);
        int isBig4 = comp2.compare(b2,c2);

    }

    // 학급 대소 비교 익명 객체
    public static Comparator<Student2> comp = new Comparator<Student2>() {
        @Override
        public int compare(Student2 o1, Student2 o2) {
            return o1.classNumber - o2.classNumber;
        }
    };

    // 나이 대소 비교 익명 객체
    public static Comparator<Student2> comp2 = new Comparator<Student2>() {
        @Override
        public int compare(Student2 o1, Student2 o2) {
            return o1.age - o2.age;
        }
    };

}

class Student implements Comparable<Student> {

    int age;			// 나이
    int classNumber;	// 학급

    Student(int age, int classNumber) {
        this.age = age;
        this.classNumber = classNumber;
    }
    @Override
    public int compareTo(Student o){
        return this.age - o.age;
    }
}

class Student2 implements Comparator<Student2> {

    int age;			// 나이
    int classNumber;	// 학급

    Student2(int age, int classNumber) {
        this.age = age;
        this.classNumber = classNumber;
    }

    @Override
    public int compare(Student2 o1, Student2 o2){
        return o1.classNumber - o2.classNumber;
    }
}

