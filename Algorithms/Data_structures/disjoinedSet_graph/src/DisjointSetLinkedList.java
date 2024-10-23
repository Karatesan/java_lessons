public class DisjointSetLinkedList implements DisjointSetDataStructure {

    private class Element{
        int representant;
        int next;
        int length;
        int last;
    }

    private static final int NULL=-1;

    Element arr[];

    public DisjointSetLinkedList(int size) {
        arr = new Element[size];
        for(int i=0;i<size;++i){
            makeSet(i);
        }
    }

    @Override
    public void makeSet(int item) {
        Element el = new Element();
        el.representant = item;
        el.length=1;
        el.next = NULL;
        el.last = item;
        arr[item] = el;
    }

    @Override
    public int findSet(int item) {
        return arr[item].representant;
    }

    @Override
    public boolean union(int itemA, int itemB) {
        int reprezentantA = findSet(itemA);
        int reprezentantB = findSet(itemB);

        if (reprezentantA == reprezentantB) {
            return false;
        }

        Element setA = arr[reprezentantA];
        Element setB = arr[reprezentantB];

        // laczymy mniejszy zbior do wiekszego czyli A do B
        if (setA.length >= setB.length) {
            // dodaemy pierwszy element czyli reprezentanta mniejszego zbioru jako element po ostatnim lasta wiekszego
            arr[setA.last].next = reprezentantB;
            //nowym lastem staje sie last dodawanego zbioru
            setA.last = setB.last;
            //aktualizujemy rozmiar
            setA.length += setB.length;

            // w zbiorze b, ktory dodalismy do a, aktualizujemy wszedzie reprezentantow
            int current = reprezentantB;
            while (current != NULL) {
                arr[current].representant = reprezentantA;
                current = arr[current].next;
            }
        } else {
            // i na odwrot B do A
            arr[setB.last].next = reprezentantA;
            setB.last = setA.last;
            setB.length+=setA.length;


            // i znwou akltaulzujemy reprezentantow w zbiorze A
            int current = reprezentantA;
            while (current != NULL) {
                arr[current].representant = reprezentantB;
                current = arr[current].next;
            }
        }
        return true;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                int representant = findSet(i);
                sb.append(representant).append(": ");
                int current = i;
                while (current != NULL) {
                    sb.append(current);
                    visited[current] = true;
                    current = arr[current].next;
                    if (current != NULL) {
                        sb.append(" -> ");
                    }
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }

public static void main(String[] args){
    DisjointSetLinkedList ds = new DisjointSetLinkedList(5);
    ds.union(0, 1);
    ds.union(3, 4);
    ds.union(1, 4);
    ds.union(1,2);
    System.out.println(ds);
}

}
