package 자바공부.모던자바.Chapter6;

import 자바공부.모던자바.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Chapter6 {
	public static void main(String[] args) {
		List<Dish> menu = Arrays.asList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH)
		);

		// chapter 6.2.4 범용 리듀싱 요약 연산
		// note 지금까지 살펴본 모든 컬렉터는 reducing 팩토리 메서드로도 정의 가능
		int totalCalories = menu.stream().map(dish -> dish.getCalories()).reduce(0, (i, j) -> i + j);
		Optional<Dish> mostCaloriesDish = menu.stream().reduce((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2);
		System.out.println(totalCalories);
		System.out.println(mostCaloriesDish);


		// chapter 6.3 그룹화
		// 음식 타입으로 그룹화
		Map<Dish.Type, List<Dish>> dishsByType = menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(dishsByType);

		// 400 칼로리 이하를 Diet, 700 이하를 Normal, 이상은 Fat
		Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
				groupingBy(dish -> {
					if (dish.getCalories() <= 400 ) return CaloricLevel.DIET;
					else if (dish.getCalories() <= 700 ) return CaloricLevel.NORMAL;
					else return CaloricLevel.FAT;
				}));
		System.out.println(dishesByCaloricLevel);


		// chapter 6.3.1 그룹화된 요소 조작
		// 해당 방식으로는 Type에서 Fish가 없어짐 (조건을 만족하지 않기 때문)
		Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
				.filter(dish -> dish.getCalories() > 500)
				.collect(Collectors.groupingBy(dish -> dish.getType()));
		System.out.println(caloricDishesByType);

		// filtering 메서드는 Collectors클래스의 또다른 정적 팩토리 메서드로 Predicate를 인수로받고, 각 그루브이 요소를 요소와 필터링된 요소로 재그룹화
		caloricDishesByType = menu.stream()
				.collect(Collectors.groupingBy(dish -> dish.getType(),
						Collectors.filtering(dish -> dish.getCalories() > 500, Collectors.toList())));
		System.out.println(caloricDishesByType);

		Map<String, List<String>> dishTags = new HashMap<>();
		dishTags.put("pork", Arrays.asList("greasy", "salty"));
		dishTags.put("beef", Arrays.asList("salty", "roasted"));
		dishTags.put("chicken", Arrays.asList("fried", "crisp"));
		dishTags.put("rice", Arrays.asList("light", "natural"));

		Map<Dish.Type, Set<String>> dishNamesByTypes = menu.stream()
				.collect(Collectors.groupingBy(dish -> dish.getType(),
						Collectors.flatMapping(dish -> dishTags.getOrDefault(dish.getName(), Arrays.asList("")).stream(),
								Collectors.toSet())));
		System.out.println(dishNamesByTypes);



		// chapter 6.3.2 다수준 그룹화
		// note 두 인수를 받는 팩토리 메서드 Collectors.groupingBy를 이용해서 항목을 다수준으로 그룹화도 가능
		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
				.collect(Collectors.groupingBy(
						dish -> dish.getType(),
						groupingBy(
								dish ->{
									if(dish.getCalories() <= 400) return CaloricLevel.DIET;
									else if(dish.getCalories() <= 700) return CaloricLevel.NORMAL;
									else return CaloricLevel.FAT;
								}
						)
				));
		System.out.println(dishesByTypeCaloricLevel);


		// chapter 6.3.3 서브그룹으로 데이터 수집
		// note groupingBy 메서드의 2번째 인수로 전달받는 컬렉터의 형식은 제한이 없다
		// 	분류 함수 한개의 인수를 갖는 groupingBy(f)는 groupingBy(f, toList())의 축약형일 뿐이며, 다양한 컬렉터를 전달받을 수 있음

		Map<Dish.Type, Long> typesCount = menu.stream().collect(Collectors.groupingBy(dish -> dish.getType(), Collectors.counting()));
		System.out.println(typesCount);

		Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
				.collect(groupingBy(dish -> dish.getType(), Collectors.maxBy(Comparator.comparingInt(dish -> dish.getCalories()))));
		System.out.println(mostCaloricByType);

		// note collectingAndThen 팩토리 메서드로 마지막에 Optional을 제거할 수 있음
		Map<Dish.Type, Dish> mostCaloricByType2 = menu.stream()
				.collect(groupingBy(
						dish -> dish.getType(),
						Collectors.collectingAndThen(
								maxBy(Comparator.comparingInt(dish -> dish.getCalories())),
								o -> o.get())));

		System.out.println(mostCaloricByType2);



	}

	public enum CaloricLevel { DIET, NORMAL, FAT }

}
