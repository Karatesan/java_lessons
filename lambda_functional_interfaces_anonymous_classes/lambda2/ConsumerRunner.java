import java.util.List;
import java.util.function.Consumer;

public class ConsumerRunner {

    public static void zrobCosZLista(Consumer<String> c, List<String>list){
        for(String s : list){
            c.accept(s);
        }
    }

    public static void main(String[] args){

        List<String> names = List.of("Ania", "Pawel", "Piotrek", "Zbyszek");

        System.out.println("======================================================");

        names.forEach(x -> System.out.println("Wyslano email do uzytkownika "+x));
    }
}
