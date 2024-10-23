package Lambda3_functional_interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateRunner {

//    Kolejnym bardzo ważnym interfejsem (bardzo często używany w strumieniach)
//    jest Predicate czyli predykat - umożliwia zapisanie zachowania "czy dany element spełnia warunek".
//    Z jego pomocą definiujemy metodę test, która przyjmuje element i zwraca wartość typu boolean -
//    czy element spełnia warunek.



    public static void main(String[] args){
        List<String> strings = new ArrayList<>(Arrays.asList("Ala","ma","kota","kot","ma","ALĘ"));
        Predicate<Integer> isLowerThan10 = x->x<10;
        int i =100;
        System.out.println(isLowerThan10.test(i));
        System.out.println(isLowerThan10.and(x->x>5).test(i));
        System.out.println(isLowerThan10.negate().test(i));
        System.out.println(isLowerThan10.or(x->x>50).test(i));

        printFiltered(s->s.contains("t"),strings);
        printFiltered(s->s.toLowerCase().equals(s),strings);
    }

  public static void printFiltered(Predicate<String>p, List<String>list){
        for(String s : list){
            if(p.test(s)) System.out.print(s+ " ");
        }
    }


}
