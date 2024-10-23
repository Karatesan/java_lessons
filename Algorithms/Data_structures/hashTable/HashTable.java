import java.util.LinkedList;

public class HashTable {
    LinkedList arr[]; // use pure array
    private final static int defaultInitSize = 8;
    private final static double defaultMaxLoadFactor = 0.7;
    private int size; //liczba elementow
    private int capacity;//rozmiaar tablicy list
    private final double maxLoadFactor;

    public HashTable() {
        this(defaultInitSize);
    }

    public HashTable(int size) {
        this(size, defaultMaxLoadFactor);
    }

    public HashTable(int initCapacity, double maxLF) {
        this.maxLoadFactor = maxLF;
        this.capacity = initCapacity;
        this.size = 0;
        arr = new LinkedList[capacity];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    //==========================================================================================

    public Object get(Object toFind) {
        int index = toFind.hashCode() % capacity;

        if (arr[index] != null) {
            for (Object elem : arr[index]) {
                if (elem.equals(toFind)) return elem;
            }
        }
        return null;
    }

    public boolean add(Object elem) {
        //sprawdzamy czy element istnieje juz w tablicy (nie moze byc powtorek o tym samy kluczu)
        if (this.get(elem) != null) return false;

        // obliczamy index listy w tablicy
        int index = elem.hashCode() % capacity;

        // tworzymy nowa liste jak nie istnieje
        if (arr[index] == null) {
            arr[index] = new LinkedList<>();
        }

        // Add element
        arr[index].add(elem);
        size++;

        // Check load factor
        if ((double) size / capacity >= maxLoadFactor) {
            doubleArray();
        }
        return true;
    }

    private void doubleArray() {
        int new_capacity = capacity * 2;
        LinkedList[] new_arr = new LinkedList[new_capacity];

    //size to ilosc elementow w tablicy a capacity to ilosc kubelkow czyli linkedList w tablicy
        for (int i = 0; i < capacity; ++i) {
            if (arr[i] != null) {

                for ( Object elem : arr[i] ) {
                    int newIndex = elem.hashCode() % new_capacity;

                    if (new_arr[newIndex] == null) {
                        new_arr[newIndex] = new LinkedList<>();
                    }
                    new_arr[newIndex].add(elem);
                }
            }
        }
        arr = new_arr;
        capacity = new_capacity;
    }


    @Override
    public String toString() {
        //TODO
        // use	IWithName x=(IWithName)elem;

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity; i++) {
            if ( arr[i] != null ) {
                for (Object elem : arr[i]) {
                    //tak jak wyzej ejst napisanem castujemy na IWithName co sprawia,
                    // ze jezeli elem implementuje intefejs IWithName to mamy dostep do metod tego interfejsu

                    IWithName x = (IWithName) elem;
                    sb.append(x.getName()).append(", ");
                }
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // remove last comma and space
        }
        sb.append("}");
        return sb.toString();
    }


}
