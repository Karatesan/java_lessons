import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearch {


    public static <T>int binarySearch(List<T>arr, Comparator<T> comp, T value){

        int left = 0;
        int right = arr.size()-1;
        int mid;

        while(left<=right){
            mid = (right+left)/2;
            int result = comp.compare(arr.get(mid),value);

            if(result == 0) return mid;
            if(result > 0) right = mid-1;
            if(result< 0) left = mid+1;
        }
        return -1;
    }

    public static void main(String[] args){

        Document document = new Document("abcd");
        System.out.println(document.hashCode());



    }


}
