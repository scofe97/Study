package 자바공부.모던자바;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Chapter2 {

	public static void main(String[] args) {

		List<Apple> inventory = new ArrayList<>();
		inventory.add(new Apple(Color.red, 100));
		inventory.add(new Apple(Color.red, 200));
		inventory.add(new Apple(Color.green, 50));
		inventory.add(new Apple(Color.green, 300));
		inventory.add(new Apple(Color.green, 120));

		for (Apple apple : filterGreenApples(inventory)) {
			System.out.println(apple);
		}
		
		System.out.println("---");

		for (Apple apple : filterApplesByColor(inventory, Color.RED)) {
			System.out.println(apple);
		}
		
		System.out.println("---");
		
		for (Apple apple : filterApplesByWeight(inventory, 150)) {
			System.out.println(apple);
		}

	}

	// 녹색 사과 필터링
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor() == Color.GREEN) {
				result.add(apple);
			}
		}
		return result;
	}

	// 색을 파라미터화
	public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor() == color) {
				result.add(apple);
			}
		}
		return result;
	}
	
	// 무게 필터링
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate p){
		List<Apple> result = new ArrayList<>();
		for(Apple apple : inventory){
			if(p.test(apple)){
				result.add(apple);
			}
		}
		return result;
	}
}

class Apple {
	Color color;
	int weight;

	public Apple(Color color, int weight) {
		this.color = color;
		this.weight = weight;
	}

	Color getColor() {
		return this.color;
	}

	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.valueOf(color) + " : " + weight;
	}
}

interface ApplePredicate{
	boolean test(Apple apple);
}

class AppleHeavyWeightPredicate implements ApplePredicate{
	public boolean test(Apple apple){
		return apple.getWeight() > 150;
	}
}

class AppleGreenColorPredicate implements ApplePredicate{
	public boolean test(Apple apple){
		return apple.getColor() == Color.GREEN;
	}
}

