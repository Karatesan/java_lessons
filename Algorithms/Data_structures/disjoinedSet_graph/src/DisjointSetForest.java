import java.util.HashSet;
import java.util.Set;

public class DisjointSetForest implements DisjointSetDataStructure {

    private class Element {
        int rank;
        int parent;
    }

    Element[] arr;

    public DisjointSetForest(int size) {
        arr = new Element[size];
        for (int i = 0; i < size; i++) {
            makeSet(i);
        }
    }

    @Override
    public void makeSet(int item) {
        Element element = new Element();
        element.rank = 0;
        element.parent = item;
        arr[item] = element;
    }

    @Override
    public int findSet(int item) {
        if (arr[item].parent != item) {
            arr[item].parent = findSet(arr[item].parent); // kompresja sciezki
        }
        return arr[item].parent;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        int rootA = findSet(itemA);
        int rootB = findSet(itemB);

        if (rootA == rootB) {
            return false;
        }

        Element setA = arr[rootA];
        Element setB = arr[rootB];

        // Łaczymy ranga, czyli zbior z wieksza ranga pochlanai drugi
        if (setA.rank > setB.rank) {
            setB.parent = rootA;
        } else if (setA.rank < setB.rank) {
            setA.parent = rootB;
        } else {
            setB.parent = rootA;
            setA.rank++;
        }
        return true;
    }

    public int countSets() {
        Set<Integer> uniqueParents = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            uniqueParents.add(findSet(i));
        }
        return uniqueParents.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append("Element ").append(i)
                    .append(": Parent = ").append(arr[i].parent)
                    .append(", Rank = ").append(arr[i].rank)
                    .append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        DisjointSetForest dsf = new DisjointSetForest(5);
        System.out.println(dsf);
        dsf.union(1, 2);
        dsf.union(0,1);
        dsf.union(3, 4);
        dsf.union(2, 3);
        System.out.println(dsf);
        System.out.println("Find set of 4: " + dsf.findSet(4));
        System.out.println(dsf);
    }
}
