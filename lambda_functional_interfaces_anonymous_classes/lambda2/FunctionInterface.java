import java.util.List;
import java.util.function.Function;

public class FunctionInterface {

    public static void main(String[] args){

        List<String> names = List.of("Ania", "Pawel", "Piotrek", "Zbyszek");

        printCos(s->s.length(),names);
    }

    public static void printCos(Function<String, Integer> f, List<String> list){

        for(String s: list){
            System.out.println(f.apply(s));
        }
    }
}
