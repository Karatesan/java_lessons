import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateInterface {


    public static void main(String[] args){

        Predicate<String> biggerThan5 = x->x.length()>5;
        List<String> names = List.of("ania", "Pawel", "Piotrek", "zbyszek", "DASDSAD", "ASD");

        int i = 20;

        printFiltered(biggerThan5.or(s->s.equals(s.toLowerCase())), x-> System.out.println(x), names);
    }
    public static void printFiltered(Predicate<String>p, Consumer<String> c, List<String>list){
        for(String s: list){
            if(p.test(s)) c.accept(s);
        }
    }

}
