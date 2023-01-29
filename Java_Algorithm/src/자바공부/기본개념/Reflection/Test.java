package 자바공부.기본개념.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Test {

    public static void main(String[] args) throws Exception {
        // chapter 1. 클래스 가져오기
        Class<Child> clazz = Child.class;
        System.out.println("Class name: " + clazz.getName());

        // chapter 2. 생성자 가져오기
        Class clazz2 = Class.forName("자바공부.기본개념.Reflection.Child");
        Constructor<Child> constructor = clazz2.getDeclaredConstructor();
        System.out.println("Constructor: " + constructor.getName());

        // chapter 3. 메소드 뽑기
        Method method1 = clazz2.getDeclaredMethod("method4", int.class);
        System.out.println("Child의 method4 찾음 :" + method1);

        // note 인자값이 여러개인 경우
        Class partypes[] = new Class[2];
        partypes[0] = int.class;
        partypes[1] = int.class;
        Method method2 = clazz2.getDeclaredMethod("method5", partypes);
        System.out.println("Child의 method4 찾음 :" + method2);

        // note getMethod()는 public 메소드를 전부 리턴하고 상속받은 메소드도 다 가져옴
        Method methods2[] = clazz2.getMethods();
        for (Method method : methods2) {
            System.out.println("Get public methods in both Parent and Child: " + method);
        }

        // chapter 4. 변수 변경
        Field field = clazz2.getDeclaredField("cstr1");
        System.out.println("Child의 변수 찾음 : " + field);

        Field fields[] = clazz.getDeclaredFields();
        for (Field field2 : fields) {
            System.out.println("Get fields in Child: " + field2);
        }

        // chapter 5. 메소드 호출
        Child child = new Child();
        Method method051 = clazz2.getDeclaredMethod("method4", int.class);
        int returnValue = (int) method051.invoke(child, 10);
        System.out.println("return value : " + returnValue);

        Class clazz3 = Class.forName("자바공부.기본개념.Reflection.Parent");
        Method method052 = clazz3.getDeclaredMethod("method1");
        method052.setAccessible(true); // private 메소드에 접근이 가능함
        method052.invoke(child);


        // chapter 6. Field
        Field fid = clazz2.getField("cstr1");
        System.out.println("chid cstr1 : " + fid.get(child));

        fid.set(child, "cstr1");
        System.out.println("child cstr1 : " + fid.get(child));

    }
}