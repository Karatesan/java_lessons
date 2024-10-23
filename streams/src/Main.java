import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {



    public static void main(String[] args) {

        List<Client> users = Arrays.asList(
                new Client("Charlie", Optional.of("charlie@example.com")),
                new Client("Alice", Optional.of("alice@example.com")),
                new Client("Bob", Optional.empty()),
                new Client("Dave", Optional.empty())
        );


        Optional<String> noEmailFound = users.stream()
                .map(o -> o.getEmail())
                .filter(email -> email.isPresent())
                .findFirst()
                .orElse(Optional.of("No email found"));

        System.out.println(noEmailFound);

//2============================================================================================================

        List<Person2> people = Arrays.asList(
                new Person2("Alice", Optional.of("USA")),
                new Person2("Bob", Optional.of("UK")),
                new Person2("Charlie", Optional.empty()),
                new Person2("Dave", Optional.of("USA")),
                new Person2("Eve", Optional.of("UK")),
                new Person2("Frank", Optional.of("Canada"))
        );

        List<String> strings = Arrays.asList("apple", "banana", "cherry", "date");


        //System.out.println(strings.stream()
             //   .map(s->s.toUpperCase())
              //  .toList());
       // System.out.println("=============================");
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        numbers.stream()
                .mapToInt(i-> i)
                .sum();


       // System.out.println(numbers.stream().max(Integer::compareTo));


       // System.out.println(collect1);


        List<Person> persons = List.of(
                new Person("Anna", 28, "Female", "Warsaw"),
                new Person("Jakub", 34, "Male", "Warsaw"),
                new Person("Maria", 45, "Female", "Gdansk"),
                new Person("Pawel", 22, "Male", "Krakow"),
                new Person("Zofia", 30, "Female", "Krakow"),
                new Person("Michal", 38, "Male", "Lodz"),
                new Person("Katarzyna", 26, "Female", "Lodz"),
                new Person("Tomasz", 50, "Male", "Gdansk"),
                new Person("Magdalena", 29, "Female", "Poznan"),
                new Person("Rafal", 41, "Male", "Poznan")
        );

        Map<String, List<Person>> byCity = persons.stream()
                .collect(Collectors.groupingBy(p -> p.miasto()));
        System.out.println("======================");

        Map<String, List<Person>> under30 = persons.stream()
                .filter(p -> p.wiek() < 30)
                .filter(p->p.plec().equals("Female"))
                .collect( Collectors.groupingBy(p -> p.miasto()) );

        Map<String, Double> sredniaWieku = persons.stream()
                .collect(Collectors.groupingBy(p -> p.miasto(), Collectors.averagingDouble(p -> p.wiek())));

        Map<String, Long> collect = persons.stream()
                .collect(Collectors.groupingBy(p -> p.miasto(), Collectors.counting()));

        System.out.println(collect);

        // System.out.println(under30);




        //tworzenie strumienia w tablicy
        int[] tablica = {1,2,3,4};
        IntStream stream = Arrays.stream(tablica);

        //tworzenie strumienia z mapy
        Map<Integer, String> mapa = new HashMap<>();
        Stream<Map.Entry<Integer, String>> stream1 = mapa.entrySet().stream();

        //zbiorniki, ktore implementuja Collection np. listy

        Stream<Person> stream2 = persons.stream();


        //=============================================================================

        //Terminal operations - koncza strumien

        //Intermediate operations - nie koncza strumienia, zwracaja nowy strumien

        List<Person> lista = persons.stream()
                .filter(p -> p.wiek() > 30)
                .peek( p -> System.out.println(p) )
                .sorted( (p1,p2)-> p1.wiek()-p2.wiek())
                .collect(Collectors.toList());

        //liczenie elementow ze strumienia

        Long female = persons.stream()
                .filter(p -> p.plec().equals("Female"))
                .collect(Collectors.counting());

        Long female1 = persons.stream()
                .filter(p -> p.plec().equals("Female"))
                .count();

        //System.out.println("Ilosc kobiet: "+female);

        //znajdowanie maxwymalnej wartosci lub obiekto wg podanych wymagan

        Optional<Person> oldestFemale = persons.stream()
                .filter(p -> p.plec().equals("Female"))
                .max((p1,p2)->Integer.compare(p1.wiek(),p2.wiek()));
        System.out.println("==============");
        System.out.println(oldestFemale.get());


        //tworzenie optionali

//        Optional.of() - nie moze byc null, jak bedzie null to wywala nullpointerexception
//        Optional.ofNullable() - dopuszcza wystapienie nulla

        //System.out.println(oldestFemale.orElse(new Person("Anna", 28, "Female", "Warsaw")));
        oldestFemale.orElseThrow(() -> new RuntimeException("osoba nie istnieje"));

        persons.stream()
                .filter(p -> p.miasto().equals("Warsaw"))
                .map( p -> p.imie() )
                .collect(Collectors.toList());

        String warsaw = persons.stream()
                .filter(p -> p.miasto().equals("Warsaw"))
                .map(p -> p.imie())
                .collect(Collectors.joining(" "));
        //System.out.println(warsaw);
    }
}

//List<String>lista = Arrays.asList("dasda", "dasdasdsadas", "aaaa", "zdszdz");
//List<Person>ludzie = Arrays.asList(new Person("Maciek", 30), new Person("Karol", 30), new Person("Anka", 15), new Person("piotrek", 60));
//
//Predicate<String> p = s ->s.length()>5;
//printIf(lista, p.negate());
//
//
//        //Consumer
////       for(String s : lista){
////           System.out.println("Slowo "+s);
////       }
//
//        lista.forEach(s-> System.out.println("Slowo "+s));


//
//List<String>lista = Arrays.asList("dasda", "dasdasdsadas", "aaaa", "zdszdz");
//
//Comparator<String>cmp = new Comparator<String>() {
//    @Override
//    public int compare(String o1, String o2) {
//        return o1.compareTo(o2);
//    }
//};
//List<Person>ludzie = Arrays.asList(new Person("Maciek", 30), new Person("Karol", 30), new Person("Anka", 15), new Person("piotrek", 60));
//
//Comparator<String> lambda = (s1,s2)->{ return s2.compareTo(s1); };
//        lista.sort((String s1, String s2)-> { return s2.compareTo(s1); });
//
//Comparator<Person> pCmp = (p1,p2)-> p1.wiek()-p2.wiek();
//
////pCmp.thenComparing((p1,p2)-> p2.imie().compareTo(p1.imie()));
//
////ludzie.sort( (p1,p2)-> p1.wiek()-p2.wiek() );
//
////ludzie.sort(pCmp.thenComparing((p1,p2)-> p1.imie().compareTo(p2.imie())));
//
////COMPARATOR.COMPARING
//
//
//        System.out.println(ludzie);
//
