import java.util.Objects;

public class Tets {
    //alt+insert
    private String s;
    private int i;
    private int i2;

    public Tets(String s, int i, int i2) {
        this.s = s;
        this.i = i;
        this.i2 = i2;
    }

    public Tets() {

    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Tets tets = (Tets) object;
        return i == tets.i && i2 == tets.i2 && Objects.equals(s, tets.s);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), s, i, i2);
    }
}
