package 자바공부.모던자바.Chapter8;

import java.util.HashMap;
import java.util.Map;

import static java.util.Map.entry;

public class Chapter8 {
    public static void main(String[] args) {
        Map<String, String> favoriteMovies = Map.ofEntries(
                entry("Raphael", "Star Wars"),
                entry("Olivia", "James Bond"));

        System.out.println(favoriteMovies.getOrDefault("Olivia", "Matrix")); //James Bond 출력
        System.out.println(favoriteMovies.getOrDefault("Thibaut", "Matrix")); //Matrix 출력

        String key = "Raphael";
        String value = "Jack Reacher 2";
        favoriteMovies.remove(key, value);

        //replaceAll 을 적용할 것이므로 바꿀 수 있는 맵을 사용해야 한다.
        Map<String, String> favoriteMovies2 = new HashMap<>();
        favoriteMovies2.put("Raphael", "Star Wars");
        favoriteMovies2.put("Olivia", "James Bond");
        favoriteMovies2.replaceAll((friend, movie) -> movie.toUpperCase());

        Map<String, String> family = Map.ofEntries(
                entry("Teo", "Star Wars"),
                entry("Cristina", "James Bond"));

        Map<String, String> friends = Map.ofEntries(
                entry("Raphael", "Star wars"));

        Map<String, String> everyone = new HashMap<>(family);
        everyone.putAll(friends);
    }
}
