import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//Consumer - zrob cos na liscie klientow
//predicate - filtrowanie listy
//comparator - porownujemy elementy, rozncie z comparable, thenComparing

public class Main {
    public static void main(String[] args) {
        isEvenCondition filtr = new isEvenCondition();

        printNumbers(new numberCondition() {
            @Override
            public boolean checkCondition(int i) {
                return i % 2 == 0;
            }
        });

        // lambda pozwala zaimplementowac interfejs w mozliwie najkrotszy sposob
        // lambdy dzialaja TYLKO z tzw. interfejsami funkcyjnymi czyli ktore maja TYLKO 1 metode

        //klasa anonimowa
        numberCondition filters = new numberCondition() {
            @Override
            public boolean checkCondition(int i) {
                return i % 2 == 0;
            }
        };

        //(int i) - argumkenty jakie przyjmuje metoda w interfejsie
        // -> po strzalce jest implementacja metody
        numberCondition lambdaFilter = ( int number ) -> {  return number % 2 == 0; };
        //pozbycie sie nawiasu dziala tylko jak funkcja ma jeden argument
        numberCondition lambdaFilter2 =   number  -> {  return number % 2 == 0; };
        numberCondition lambdaFilter3 =   number  -> number % 2 == 0;


        ArrayList<Integer>list = new ArrayList<>(List.of(4,5,765,12,123,645,5));
        System.out.println(list);
        Collections.sort(list,(x,y)->y-x);
        System.out.println(list);

        List<Student> students = new ArrayList<>();
        students.add(new Student("Charlie", 20));
        students.add(new Student("Charlie", 30));
        students.add(new Student("Eve", 22));
        students.add(new Student("Alice", 20));
        students.add(new Student("Bob", 22));
        students.add(new Student("Dave", 21));


        Comparator<Student> cmp = (s1,s2) -> s1.getName().compareTo(s2.getName());
        cmp = cmp.thenComparing((s1,s2)-> s2.getAge()-s1.getAge());

        Collections.sort(students,cmp);
        System.out.println(students);


    }

    public static void printNumbers( numberCondition filter ) {
        for (int i = 0; i < 100; i++) {
            if (filter.checkCondition(i)) {
                System.out.println(i);
            }
        }
    }
}

class isOddCondition implements numberCondition {
    @Override
    public boolean checkCondition(int i) {
        return i % 2 != 0;
    }
}

class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

