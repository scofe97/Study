package 자바공부.객체지향.다형성;

public class EX_Polymorphism1 {
    public static void main(String[] args) {

        // chapter 1. 다형성의 개념
        // note 하나의 객체가 여러 타입을 가질 수 있다는 의미

        // note 참조변수의 다형성

        Parent pa = new Parent();
        Child ch = new Child();
        Parent pc = new Child();
        Parent pb = new Brother();

        // note instanceof -> 왼쪽에 전달된 변수가, 실제로 참조하고 있는 인스턴스 타입이면 true
        System.out.println(pa instanceof Parent); // Parent 객체를 받음
        System.out.println(ch instanceof Parent); // 상속받아 true
        System.out.println(pc instanceof Parent); // 상속받아 true
        System.out.println(pb instanceof Parent); // 상속받아 true


        // chapter 2. 추상 클래스스
        Animal c = new Cat();
        Animal d = new Dog();

        c.cry();
        d.cry();


        // chapter 익명 클래스
        Animal a = new Animal() {
            @Override
            void cry() {
                System.out.println("test");
            }
        };

        a.cry();

    }

    class inner{

    }
}

class Parent{ }
class Child extends Parent{ }
class Brother extends Parent{ }


abstract class Animal {abstract void cry();}
class Cat extends Animal {void cry(){
    System.out.println("냐옹냐옹");
}}
class Dog extends Animal {void cry(){
    System.out.println("멍멍");
}}


interface Animal2 {abstract void cry();}
class Cat2 implements Animal2 {public void cry(){
    System.out.println("냐옹냐옹");
}}
class Dog2 implements Animal2 {public void cry(){
    System.out.println("멍멍");
}}
