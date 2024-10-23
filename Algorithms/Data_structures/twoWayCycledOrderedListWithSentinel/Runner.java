import java.util.ArrayList;
import java.util.List;

public class Runner {



    public static void main(String[] args){

        double d = 10.3;
        int i = (int) d;
        double d2 = i;



        HashTable ht = new HashTable();
        ht.add(new Document("One"));
        ht.add(new Document("Two"));
        ht.add(new Document("Three"));
        ht.add(new Document("Four"));
        System.out.println("capacity " + ht.getCapacity()+" size "+ht.getSize());

        System.out.println(ht.toString());   // Should print all elements in the hash table
    }
    }

