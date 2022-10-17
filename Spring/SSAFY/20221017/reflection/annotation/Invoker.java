package reflection.annotation;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
자바의 리플렉션
    자바에서 기본적으로 제공하는 API로써
    클래스, 인터페이스, 메서드등을 찾을 수 있고
    객체를 생성하거나 변수를 변경할 수도 있고
    메서드를 호출할 수도 있습니다.

    Reflection은 다음과 같은 정보를 가져올 수 있다
    - class
    - Constructor
    - Method
    - Field

    private 요소도 접근 가능
 */
public class Invoker {

    public void invoke(Object obj) throws InvocationTargetException, IllegalAccessException {

        Class clazz = obj.getClass();

        Method[] methods = clazz.getDeclaredMethods();
        Method[] orders = new Method[methods.length];

        for (Method m : methods) {
            if(m.isAnnotationPresent(Order.class)){
                Order annotation = m.getAnnotation(Order.class);
                orders[annotation.number()-1] = m;
            }
        }

        for (Method m : orders) {
            m.invoke(obj, null);
        }
    }
}
