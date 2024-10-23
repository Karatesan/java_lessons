import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Comparator_Comparable {

    //Comparator
    /*
    tworzac obiekt klasy comparator mozemy to robic wewnatrz sorta lub jako osobny obiekt do pozniejszego uzycia
    mozna uzyc wbudowanych funkcji jak reverse czy thenComparing
     */
    static class AgeComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }

    static class NameComparator implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
            return o1.name.compareTo(o2.name);
        }
    }

    public static void main(String[] args) {


//Comparable

//        zwróć coś mniejszego od 0, jeżeli jesteś mniejszy od o
//        zwróć 0, jeżeli jesteś równy o
//        zwróć coś większego od 0, jeżeli jesteś większy od o

//        Jak dziala sort:
//        If compareTo() returns a negative integer, it means that the current element should come before the compared element in the sorted order.
//        If compareTo() returns zero, it means that the current element is equal to the compared element, and their relative order doesn't matter.
//        If compareTo() returns a positive integer, it means that the current element should come after the compared element in the sorted order.
        // jezeli compare zwroci <0 to element jeste.e traktowany jako mniejszy i ustawiany pierwszy,
        //jezeli compare zwroci >0 to element jest traktowany jak wiekszy i ustawiany jest drugi

//        Student s1 = new Student("Maciek",13);
//        Student s2 = new Student("Krzysiek",10);
//        Student s3 = new Student("Andrzej",10);
//        Student s4 = new Student("Pawel",30);
//
//        List<Student> s = new ArrayList<>(List.of(s1,s2,s3,s4));
//        Collections.sort(s);
//        System.out.println(s);
//        Comparator<Student> age = new StudentComparators.AgeComparator();
//        Comparator<Student> name = new NameComparator();
//        Comparator<Student> combined = age.thenComparing(name);
//
//        Comparator<Student> ageCopm = Comparator.comparingInt(student->student.age);
//        Collections.sort(s, new AgeComparator().thenComparing(new NameComparator().reversed()));
//        System.out.println(s);

        //==============================================================================
        String st = "dassdasda";

        Test((c)-> System.out.println(c+"dasdasdsa"),st);

        printIfBigger(200, new Compare(){
            @Override
            public boolean compare(int number){
                return number>160;
            }
        });

        printIfBigger(160, c->c>150);
        printIfBigger(170,(int c)->{return c>150;});



        //==============================================================================
        List<String> strings = new ArrayList<>(Arrays.asList("Ala","ma","kota","kot","ma","ALĘ"));

        printListIf(n->n.length()>2,strings);
        printListIf(n->n.charAt(0)==Character.toUpperCase(n.charAt(0)),strings);


    }

    // interfejs funkcyjny, które oznacza dowolony interfejs z jedną metodą, którego implementację można zapisać jako lambdę.
    public interface Compare{
        public boolean compare(int number);
    }

    //Konsument (Consumer) to najprostszy z interfejsów funkcyjnych. Służy on do zapisania operacji,
    // jaka ma być wykonana na danym elemencie. Definiuje się je poprzez zdefiniowanie implementacji z metodą accept (np. lambdą).
    public static void Test(Consumer<String>c, String s){
        c.accept(s);
    }

    public static void printIfBigger(int i, Compare c){
        if(c.compare(i)) System.out.println(i);
    }


    // predykat - umożliwia zapisanie zachowania "czy dany element spełnia warunek".
    public static void printListIf(Predicate<String> p, List<String>s){
        for(String n : s){
            if(p.test(n)) System.out.print(n+", ");
        }
        System.out.println("\n");
    }

}
