import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //int float double boolean char 
/*
        String s = "test1";
        String s1 = "test2";
        String s2 = "test3";
        String[] tablica;

        String sNew = new String("test");
        //String to kalsa z metodami statycznymi do dzialania na stringach
        //Arrays to kalsa z metodami statycznymi do dzialania na tablicach
        //Collections to kalsa z metodami statycznymi do dzialania na kontenerach klasowych tych kore dziedzicza po Collection

        List<String> lista = new ArrayList<>(Arrays.asList(s, s1, s2));
        String joinedString = String.join(",", lista);
        System.out.println(joinedString);
        String[] split = joinedString.split(",");

        //petla for eaach

//
//        for (int i = 0; i < split.length; i++) {
//            System.out.println(split[i]);
//        }
//        int i = 0;
//        while (i < split.length) {
//            System.out.println(split[i++]);
//        }

        int[] intTab = {1,2,4,6,87,2,34,7};
         Arrays.sort(intTab);
        for( int el : intTab ){
            System.out.println(el);
        }
        //Comparatory i lambda i predykaty

        int[] ints = Arrays.copyOf(intTab, intTab.length * 2);

        ints[intTab.length] = 37573485;
        for( int el : ints ){
            System.out.println(el);
        }
        //=========================================================================
        //List oraz Array static classes
        ArrayList<String> names = new ArrayList<>(List.of("Maciek", "piotrek","Krzysiek"));
        names.remove(names.indexOf("Maciek"));
        Collections.sort(names, Collections.reverseOrder());
        Collections.addAll(names,"dasdas","dasdsa","dasdasd");



*/
        //======================================================================================================================
        //        Jak dziala sort:
//        If compareTo() returns a negative integer, it means that the current element should come before the compared element in the sorted order.
//        If compareTo() returns zero, it means that the current element is equal to the compared element, and their relative order doesn't matter.
//        If compareTo() returns a positive integer, it means that the current element should come after the compared element in the sorted order.


        Prostokat p1 = new Prostokat(10,20);
        Prostokat p2 = new Prostokat(10,10);
        Prostokat p3 = new Prostokat(100,35);
        Prostokat p4 = new Prostokat(10,23);

        List<Prostokat> prostokaty = new ArrayList<>();
        prostokaty.add(p1);
        prostokaty.add(p2);
        prostokaty.add(p3);
        prostokaty.add(p4);

        compareX cx = new compareX();
        compareY cy = new compareY();
        System.out.println(prostokaty);
        Collections.sort(prostokaty, cx.reversed().thenComparing(cy.reversed()));
        Collections.sort(prostokaty, new ProstokatUtils.compareX());
        System.out.println(prostokaty);
    }

    public static class compareX implements Comparator<Prostokat>{

        @Override
        public int compare(Prostokat o1, Prostokat o2) {
            return o1.x - o2.x;
        }
    }
        public static class compareY implements Comparator<Prostokat>{

        @Override
        public int compare(Prostokat o1, Prostokat o2) {
            return o1.y - o2.y;
        }
    }

    //predykaty, Consume i lambda interfejs funkcyjny
}