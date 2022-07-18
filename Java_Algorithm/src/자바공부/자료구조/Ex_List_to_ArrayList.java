package 자바공부.자료구조;

import java.util.ArrayList;
import java.util.List;

public class Ex_List_to_ArrayList {
    public static void main(String[] args) {

        // chapter 1. 왜 List로 받는게 유리한가?
        // note 향후 삽입 삭제에 유리한 LinkedList 변경이 쉽기 때문, 유연한 구조를 가질 수 있다
        //  LinkedList 외에 Stack으로 변경도 가능
        //  List로 선언해야 List에서 제공하는 메소드까지 사용 가능

        List<Object> list = new ArrayList<Object>();
        ArrayList<Object> list2 = new ArrayList<>();

    }
}
