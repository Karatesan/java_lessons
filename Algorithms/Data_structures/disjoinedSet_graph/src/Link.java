

public class Link implements Comparable<Link> {
    String ref;
    int weight;


   // ref to nazwa dokumentu, do którego prowadzi link. Jest to identyfikator dokumentu, który jest linkowany przez aktualny dokument.
    public Link(String ref) {
        this(ref, 0);
    }

    public Link(String ref, int weight) {
        this.ref = ref;
        this.weight = weight;
    }

    @Override
    public int compareTo(Link o) {
        return this.ref.compareTo(o.ref);
    }

    public static boolean isCorrectId(String id) {
        return id.matches("[a-zA-Z0-9_]+");
    }

    static Link createLink(String linkStr) {
        linkStr = linkStr.trim();

        // Check if the string matches the pattern with optional spaces
        if (linkStr.matches("[a-zA-Z0-9_]+\\s*\\(\\s*[0-9]+\\s*\\)")) {
            String ref = linkStr.substring(0, linkStr.indexOf('(')).trim();
            int weight = Integer.parseInt(linkStr.substring(linkStr.indexOf('(') + 1, linkStr.indexOf(')')).trim());
            return new Link(ref, weight);
        } else if (isCorrectId(linkStr)) {
            return new Link(linkStr, 0); // Default weight to 0 if no weight is provided
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Link link = (Link) obj;
        return ref.equals(link.ref);
    }

    @Override
    public int hashCode() {
        return ref.hashCode();
    }

    @Override
    public String toString() {
        return ref + "(" + weight + ")";
    }
}
