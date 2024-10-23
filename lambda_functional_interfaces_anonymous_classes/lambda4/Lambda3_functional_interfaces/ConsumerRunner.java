package Lambda3_functional_interfaces;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerRunner {

    public static void perforOnList(Consumer<String> c, List<String>list){
        for(String s : list)
            c.accept(s);
    }


    //interfejs Consumer - łuży on do zapisania operacji, jaka ma być wykonana na
    // danym elemencie. Definiuje się je poprzez zdefiniowanie implementacji z metodą accept (np. lambdą).
    public static void main(String[] args){

        List<String> CUSTOMERS = Arrays.asList("Ania", "Piotrek", "Mateusz", "Kasia", "Tomek");

        System.out.println(CUSTOMERS);
        System.out.println("Drukowanie wszystkiego ======================");
        perforOnList(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        },CUSTOMERS);

        System.out.println("Drukowanie wszystkiego z malej litery ======================");

        perforOnList(s-> System.out.println(s.toLowerCase()),CUSTOMERS);

        System.out.println("dlugosc slow ======================");

       perforOnList(s-> System.out.println(s+ ", length = "+s.length()),CUSTOMERS);

       //funkcja w ziennej





    }
}
