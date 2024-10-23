package conditionsInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaRunner {

    public static void printNumbers(NumberCondition condition){
        for(int i=40;i<60;++i){
            if(condition.checkCondition(i)) System.out.println(i);
        }
        System.out.println("\n");
    }


    public static void main(String[] args){
        //4 klasa anonimowa z lambda
        // lambda jest skróconym zapisem klasy anonimowej implementującej interfejs z jedną metodą i właśnie tę metodę reprezentuje lambda.

        printNumbers(number -> number>52);
        printNumbers((int number) -> {
            return number > 52;
        });
        printNumbers((int number) -> number > 52 );

        //===================================================
//
//        Calculator adder = (int x, int y) ->  x+y;
//        Calculator subtractor = (int x, int y,int z) -> x-y-z;

        StringTransform transformator = text-> text.toLowerCase().split(" ")[0];

        System.out.println(transformator.transform("dupa kupa zupa"));

    }
}
