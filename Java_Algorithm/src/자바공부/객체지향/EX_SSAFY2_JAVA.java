package 자바공부.객체지향;

public class EX_SSAFY2_JAVA {
    public static void main(String[] args) {

    }
}

class Person {
    void jump(){
        System.out.println("두다리로 뛴다");
    }
}

class Spider{
    void jump(){
        System.out.println("대충 굉장한 점프");
    }
}

class SpiderMan2 extends Person{
    Spider spider = new Spider();
    boolean isSpider;

    void fireWeb(){

    }

    void jump(){
        if(isSpider){
            spider.jump();
        }else{
            System.out.println("두 다리로 힘껏 점프프");
        }
   }
}

// chapter 어노테이션 정의
// note 사전적 의미 : 주석,
//  컴파일러 JVM, 프레임워크 등이 보는 주석
//  소스코드에 메타 데이터를 삽입하는 형태
//  JDK 1.5의 예시,
//      @Deprecated 컴파일러에게 해당 매서드가 deprecated 되었다고 알려줌
//      @Override -> 선언된 경우 반드시 super class에 선언 되어있는 메서드여야 함


// chapter Object
// note
//  toString()
//  equals
//  hashCode

