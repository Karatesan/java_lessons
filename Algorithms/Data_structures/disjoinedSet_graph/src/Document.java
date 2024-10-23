import java.util.Scanner;
import java.util.*;

public class Document implements IWithName {
    public String name;
    public SortedSet<Link> links;

     class Node{
        int parent;
        int left;
        int right;
    }



    public Document(String name) {
        this.name = name.toLowerCase();
        links = new TreeSet<>();
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        links = new TreeSet<>();
        load(scan);
    }

    public void load(Scanner scan) {
        while (scan.hasNext()) {
            String line = scan.nextLine();
            if (line.equals("eod")) break;
            String[] parts = line.split(" ");
            for (String part : parts) {
                Link newLink = createLink(part);
                if (newLink != null) {
                    links.add(newLink);
                }
            }
        }
    }

    public static boolean isCorrectId(String id) {
        return id.matches("[a-zA-Z0-9_]+");
    }

    static Link createLink(String linkStr) {
        if (isCorrectId(linkStr)) {
            return new Link(linkStr);
        } else if (linkStr.matches("[a-zA-Z0-9_]+\\([0-9]+\\)")) {
            String ref = linkStr.substring(0, linkStr.indexOf('('));
            int weight = Integer.parseInt(linkStr.substring(linkStr.indexOf('(') + 1, linkStr.indexOf(')')));
            return new Link(ref, weight);
        }
        return null;
    }

    public void addLink(String ref, int weight) {
        Link link = new Link(ref, weight);
        links.add(link);
    }

    @Override
    public String toString() {
        StringBuilder retStr = new StringBuilder("Document: " + name + "\n");
        for (Link l : links) {
            retStr.append(l).append("\n");
        }
        return retStr.toString();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String getName() {
        return name;
    }
}
