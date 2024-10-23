package conditionsInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Runner {

    //1 ====================================================================
    //tworzymy wiele klas dla kazdego warunku
public static boolean isLessThan50(int number){
    return number <50;
}

public static boolean isNumberEven(int number){
    return number%2==0;
}


    //2 ====================================================================

public static void printNumbers(NumberCondition condition){
    for(int i=40;i<60;++i){
        if(condition.checkCondition(i)) System.out.println(i);
    }
    System.out.println("\n");
}

    //2 ====================================================================
    public static void main(String[] args){

        //1
        //System.out.println(isLessThan50(30));

        //2 uzywamy funkcji, ktora przyjmuje interfejs i tworzymy nowy obiekt klasy implementujacej ten interfejs
        printNumbers(new EvenCondition());

        //3 Klasa anonimowa jako parametr, tworzymy klase z interfejsu bezposrednio w miejscu jej uzycia


        printNumbers(new NumberCondition(){
            @Override
            public boolean checkCondition(int number){
                return number>50;
            }
        });

        //4 referencja metody
        printNumbers(Runner::isLessThan50);



    }
}
