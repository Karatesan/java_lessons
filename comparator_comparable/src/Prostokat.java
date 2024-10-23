public class Prostokat implements Comparable<Prostokat> {

    public int x;
    public int y;

    public Prostokat(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "Prostokat{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    //    zwróć coś mniejszego od 0, jeżeli jesteś mniejszy od o
    //    zwróć 0, jeżeli jesteś równy o
    //    zwróć coś większego od 0, jeżeli jesteś większy od o

    @Override
    public int compareTo(Prostokat o) {
        int diff =  o.x-this.x;
        if(diff != 0) return diff;
        else{
            return this.y - o.y;
        }
    }
}
