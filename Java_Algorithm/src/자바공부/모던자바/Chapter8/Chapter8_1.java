package 자바공부.모던자바.Chapter8;

// import static java.util.Map.Entry;

public class Chapter8_1 {
    public static void main(String[] args) {
//        List<String> friends = new ArrayList<>();
//        friends.add("Raphael");
//        friends.add("Olivia");
//        friends.add("Thibaut");
//
//        List<String> friends2 = Arrays.asList("Raphael", "Olivia");
//        friends2.set(0, "Rechard");
//        // friends2.add("Thibaut"); -> 에러 (내부적으로 고정된 크기이기 때문)
//
//        Set<String> friends3 = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
//        Set<String> friends4 = Stream.of("Raphael", "Olivia", "Thibaut").collect(Collectors.toSet());
//
//        List<String> friends5 = List.of("Raphael", "Olivia","Thibaut");
//
//        Trader raoul = new Trader("Raoul", "Cambridge");
//        Trader mario = new Trader("Mario", "Milan");
//        Trader alan = new Trader("Alan", "Cambridge");
//        Trader brian = new Trader("Brian", "Cambridge");
//
//        List<Transaction> transactions = Arrays.asList(
//                new Transaction(brian, 2011, 300),
//                new Transaction(raoul, 2012, 1000),
//                new Transaction(raoul, 2011, 400),
//                new Transaction(mario, 2012, 710),
//                new Transaction(mario, 2012, 700),
//                new Transaction(alan, 2012, 950)
//        );
//
//        friends.removeIf(n -> n.equals("Olivia"));
//        for (String S : friends) {
//            System.out.println(S);
//        }
//
//        friends.replaceAll(n -> n + "입니다.");
//        for (String S : friends) {
//            System.out.println(S);
//        }
//
//        transactions.sort(((o1, o2) -> o1.getValue() - o2.getValue()));
//        for (Transaction transaction : transactions) {
//            System.out.println(transaction);
//        }
//
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 1; i <= 10; i++) {
//            map.put(i-1, i);
//        }
//
//        map.forEach((k, v) -> System.out.println(k + " " + v));

//        Map<Integer, Integer> favouriteMovies = Map.ofEntries(
//                entry(1, 3),
//                entry(2, 2),
//                entry(3, 1));
//
//        favouriteMovies.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .forEach(i -> System.out.println(i));
//
//        Map<String, String> favouriteMovies2 = new HashMap<>(Map.ofEntries(
//                entry("Raphael", "Star Wars"),
//                entry("Olivia", "James Bond")));
//
//        System.out.println(favouriteMovies2.getOrDefault("Olivia", "Matrix")); //James Bond 출력
//        System.out.println(favouriteMovies2.getOrDefault("Thibaut", "Matrix")); //Matrix 출력
//
//        favouriteMovies2.computeIfAbsent("응애", i -> i+"응애");
//        System.out.println(favouriteMovies2.get("응애"));
//        // 응애응애
    }


}


