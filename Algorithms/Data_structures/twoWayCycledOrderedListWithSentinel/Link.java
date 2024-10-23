import java.util.Objects;

public class Link implements Comparable<Link>{
    public String ref;
    public int weight;
    public Link(String ref) {
        this.ref=ref;
        weight=1;
    }
    public Link(String ref, int weight) {
        this.ref=ref;
        this.weight=weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return weight == link.weight && Objects.equals(ref, link.ref);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ref, weight);
    }

    @Override
    public String toString() {
        return ref+"("+weight+")";
    }
    @Override
    public int compareTo(Link another) {
        return this.ref.compareTo(another.ref);
    }
}
//    zwróć coś mniejszego od 0, jeżeli jesteś mniejszy od o
//        zwróć 0, jeżeli jesteś równy o
//        zwróć coś większego od 0, jeżeli jesteś większy od o
