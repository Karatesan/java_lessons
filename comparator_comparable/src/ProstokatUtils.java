import java.util.Comparator;

public class ProstokatUtils {

    public static class compareX implements Comparator<Prostokat> {

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
}
