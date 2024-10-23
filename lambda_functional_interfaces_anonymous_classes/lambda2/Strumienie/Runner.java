package Strumienie;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    public static class Customer {
        private String firstName;
        private String lastName;
        private String city;
        private int birthYear;

        public Customer(String firstName, String lastName, String city, int birthYear) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.city = city;
            this.birthYear = birthYear;
        }

        @Override
        public String toString() {
            return "Customer{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", city='" + city + '\'' +
                    ", birthYear=" + birthYear +
                    '}';
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getBirthYear() {
            return birthYear;
        }

        public void setBirthYear(int birthYear) {
            this.birthYear = birthYear;
        }

        // konstruktory, gettery, settery
    }

//co to jest stream
//tworzenie streamow - lista, tablica, obiekty
    //podstawowe operacje - filter, map, foreach, count, peek (liczenie), groupBy, joining

    public static void main(String[] args){

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Jan", "Kowalski", "Wrocław", 1970));
        customers.add(new Customer("Adam", "Nowak", "Warszawa", 1980));
        customers.add(new Customer("Joanna", "Lipnicka", "Wrocław", 1990));
        customers.add(new Customer("Adaś", "Miauczyński", "Warszawa", 1978));
        customers.add(new Customer("Maciek", "Bladas", "Opole", 1978));
        customers.add(new Customer("Krzysiek", "IOdoas", "Warszawa", 1978));

        List<String> names = new ArrayList<>();

//        for(Customer c : customers)
//        {
//            if(c.city.equals("Warszawa")) names.add(c.lastName);
//        }

        String[] table = {"32131", "dasdada"};
        Map<String,Integer> m = new HashMap<>();

        //z tego raczej nie bedziesz korzystal
        Stream<Customer> cStream = customers.stream();
        Stream<Integer> integerStream = Stream.of(1, 2, 54, 545, 645, 67);
        Stream<String> stream = Arrays.stream(table);
        Stream<Map.Entry<String, Integer>> stream1 = m.entrySet().stream();

        Function<Customer,String> func = new Function<Customer, String>() {
            @Override
            public String apply(Customer customer) {
                return customer.lastName;
            }
        };

        names = customers.stream()
                .peek(c-> System.out.println("Customer przed obrobka " +c))
                .filter(c->c.city.equals("Warszawa"))
                .map(c->c.lastName)
                .collect(Collectors.toList());

        //System.out.println(names);

        customers.stream()
                .filter(c->c.birthYear>1980)
                .forEach(c-> System.out.println(c));

        List<Integer> numbers = new ArrayList<>(List.of(1,2,4,5,6,7,85,3,2,1,3,4,5,1,2,3,1));

        //System.out.println(numbers.stream().collect(Collectors.toSet()).stream().count());





        // System.out.println(customers.stream().filter(c->c.birthYear>=1980).count());





    }
}
