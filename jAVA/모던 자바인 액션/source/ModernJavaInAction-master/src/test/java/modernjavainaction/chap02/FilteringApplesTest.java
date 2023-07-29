package modernjavainaction.chap02;

import junit.framework.TestCase;
import modernjavainaction.chap03.Apple;
import modernjavainaction.chap03.Color;
import org.junit.Test;

import java.util.*;

public class FilteringApplesTest extends TestCase {

    private final List<Apple> inventory = List.of(
            new Apple(200, Color.RED),
            new Apple(220, Color.GREEN),
            new Apple(180, Color.GREEN),
            new Apple(200, Color.GREEN),
            new Apple(190, Color.RED));


    @Test
    public void testName() {
        ArrayList<Apple> inventory = new ArrayList<>(this.inventory);
        final var expected = List.of(
                new Apple(220, Color.GREEN),
                new Apple(200, Color.GREEN),
                new Apple(200, Color.RED),
                new Apple(190, Color.RED),
                new Apple(180, Color.GREEN));

        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        System.out.println(inventory);
    }

    @Test
    public void array() {
        int[] intArray1 = {10, 5, 8, 1, 7, 6, 3, 4, 2, 9};
        Integer[] intArray = {10, 5, 8, 1, 7, 6, 3, 4, 2, 9};

        int[] intArray2 = {4,2,1,3};
        int[] intArray3 = {4,2,1,3};

        // 정렬
        Arrays.sort(intArray1);

        // 변환
        List<Integer> list1 = Arrays.asList(intArray);
        List<Integer> list2 = List.of(intArray);
        int[] array1 = list1.stream().mapToInt(i -> i).toArray();
        int[] array2 = list2.stream().mapToInt(i -> i).toArray();

        // 복사
        Arrays.copyOf(intArray1, intArray1.length);

        // 검색
        int idx = Arrays.binarySearch(intArray1, 3);

        // 비교
        System.out.println(Arrays.equals(intArray2, intArray3));



    }
}