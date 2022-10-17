package reflection.annotation;

import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        ClassA classA = new ClassA();
        Invoker invoker = new Invoker();

        invoker.invoke(classA);
    }
}
