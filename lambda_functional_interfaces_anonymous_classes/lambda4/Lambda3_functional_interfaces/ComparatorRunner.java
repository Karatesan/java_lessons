package Lambda3_functional_interfaces;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorRunner {

//    < 0   (ujemna)	x jest MNIEJSZE od y
//      0	            x jest RÓWNE y
//    > 0   (dodatnia)	x jest WIĘKSZE od y

    public static void main(String[] args){
        Integer[] array = {5,4,8,3};
        Comparator<Integer> c = new Comparator<Integer>(){
            @Override
           public int compare(Integer i1, Integer i2){
                return i2-i1;
            }
        } ;

        Arrays.sort(array,c);
        System.out.println(Arrays.toString(array));

        String[] strings = {"Ala", "ma", "białego", "kota"};

        Arrays.sort(strings,(x,y)->x.length()-y.length());
        Arrays.sort(strings,(x,y)-> Integer.compare(y.length(),x.length()));
        System.out.println(Arrays.toString(strings));
    }
}
