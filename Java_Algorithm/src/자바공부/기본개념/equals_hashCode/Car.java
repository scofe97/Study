package 자바공부.기본개념.equals_hashCode;

import java.util.*;

public class Car {
    private final String name;

    public Car(String name) {
        this.name = name;
    }

    // intellij Generate 기능 사용
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car("foo"));
        cars.add(new Car("foo"));
        System.out.println(cars.size());
        // 2

        Set<Car> cars2 = new HashSet<>();
        cars2.add(new Car("foo"));
        cars2.add(new Car("foo"));

        System.out.println(cars2.size());
        // 2
    }
}