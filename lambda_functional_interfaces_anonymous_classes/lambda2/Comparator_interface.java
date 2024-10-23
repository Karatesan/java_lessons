import java.util.Arrays;
import java.util.Comparator;

public class Comparator_interface {

public static class Human{
    String name;
    int age;
}

    public static void main(String[] args){

        Integer[] liczby = {3,5,765,122,435,76,987,31};

        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        };

        Arrays.sort(liczby,reverseOrder.thenComparing((o1,o2)->o2-o2));

        System.out.println( Arrays.toString(liczby));

    }

}
