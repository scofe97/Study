package reflection.annotation;

// C:\SSAFY\workspace\work_spring
// reflection annotation -> 메서드 동작제어

/*
ElementType : 해당 어노테이션을 사용할 수 있는 위치

RetentionPolicy
    Source : 어노테이션을 주석처럼 사용하는 것. 컴파일러가 컴파일 할 때
    Class : 컴파일러가 컴파일 할 때 해당 어노테이션을 메모리에서 사용하지만 런타임시에는 메모리에서 제거
    RUNTIME : 어노테이션을 런타임시에도 사용가능

 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Order {
    int number() default 0 ; // 기본적으로 0으로 세팅됨
}
