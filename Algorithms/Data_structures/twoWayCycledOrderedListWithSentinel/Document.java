import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;

public class Document implements IWithName {
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> link;

    public static final int[] ciag = {7, 11, 13, 17, 19};


    public Document(String name) {
        this.name = name;
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        link = new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        //TODO
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)


    public static boolean isCorrectId(String id) {
        //TODO
        return false;
    }

    // accepted only small letters, capitalic letter, digits nad '_' (but not on the begin)
    public static Link createLink(String link) {
        if (link.charAt(0) == '_') return null;
        for (int i = 0; i < link.length(); ++i) {
            char ch = link.charAt(i);
            if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ch == '_')) {
                return null;
            }
        }
        return new Link(link);
    }

    @Override
    public String toString() {
        String retStr = "Document: " + name;
        //TODO
        return retStr;
    }

    public String toStringReverse() {
        String retStr = "Document: " + name;
        ListIterator<Link> iter = link.listIterator();
        while (iter.hasNext())
            iter.next();
        //TODO
        while (iter.hasPrevious()) {
        }
        return retStr;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int ciagIndex = 0;
        int hash_code = name.charAt(0);

        for (int i = 1; i < name.length(); ++i) {
            //System.out.println((int)name.charAt(i));
            if (ciagIndex == 5) ciagIndex = 0;
            hash_code = (hash_code * ciag[ciagIndex++] + name.charAt(i)) % 100000000;
        }
        return hash_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(name, document.name);
    }


}

