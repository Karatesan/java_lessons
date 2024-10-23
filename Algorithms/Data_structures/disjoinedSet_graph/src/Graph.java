import java.util.*;

public class Graph {

    // Macierz sąsiedztwa - tablica 2D
    int[][] arr;
    // Mapy do odwzorowania nazw dokumentów na indeksy i odwrotnie
    HashMap<String, Integer> name2Int;
    HashMap<Integer, String> int2Name;

    //Lista dokumentow przechowywana jako mapa nazwaDokumentu-Dokument
    public Graph( SortedMap<String, Document> zbior ) {
        int size = zbior.size();
        arr = new int[size][size];
        name2Int = new HashMap<>();
        int2Name = new HashMap<>();

        // Wypełniamy mapy, które pozwolą na konwersję z indeksu na nazwę dokumentu i odwrotnie
        int index = 0;
        for ( String docName : zbior.keySet() ) {
            name2Int.put(docName, index);
            int2Name.put(index, docName);
            index++;
        }
        /*
        * a-0 b-1 c-2 d-3 e-4 f-5
        * 0-a 1-b 2-c 3-d 4-e 5-f
        */

        // Wypełniamy macierz sąsiedztwa
        //set to taka jabby lista, ktora ma wylacznie unikatowe wartosci, bez powtorek
        //w mapie nazwa.keySet() zwraca set z wszystkimi kluczami (pierwsza wartosc)
        //a entrySet() zwraca set elementow o nazwie Entry, gdzie kazdy Entry ma i klucz i wartosc
        for (Map.Entry<String, Document> entry : zbior.entrySet()) {
            //kazdy entry to jest para String czyli nazwa dokumentu oraz sam Dokument
            String docName = entry.getKey();
            Document doc = entry.getValue();
            int docIndex = name2Int.get(docName);

            for (Link link : doc.links) {
                Integer linkIndex = name2Int.get(link.ref);
                if (linkIndex != null) {
                    arr[docIndex][linkIndex] = link.weight;
                }
            }
        }
    }

    public String bfs(String start) {
        if (!name2Int.containsKey(start)) return null;

        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[arr.length];
        //fifo
        Queue<Integer> queue = new LinkedList<>();

        int startIndex = name2Int.get(start);
        visited[startIndex] = true;
        queue.add(startIndex);

        while (!queue.isEmpty()) {
            int nodeIndex = queue.poll();
            result.append(int2Name.get(nodeIndex)).append(" ");

            for (int i = 0; i < arr.length; i++) {
                if ( arr[nodeIndex][i] > 0 && !visited[i] ) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return result.toString().trim();
    }

    public String dfs(String start) {
        if (!name2Int.containsKey(start)) return null;

        StringBuilder result = new StringBuilder();
        boolean[] visited = new boolean[arr.length];
        //lifo
        Stack<Integer> stack = new Stack<>();

        int startIndex = name2Int.get(start);
        stack.push(startIndex);

        while ( !stack.isEmpty() ) {
            int nodeIndex = stack.pop();

            if (!visited[nodeIndex]) {
                visited[nodeIndex] = true;
                result.append(int2Name.get(nodeIndex)).append(" ");

                for ( int i = arr.length-1; i >=0; i-- ) {
                    if ( arr[nodeIndex][i] > 0 && !visited[i] ) {
                        stack.push(i);
                    }
                }
            }
        }

        return result.toString().trim();
    }

    private static class Node {
        int index;
        int dist;

        Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }
    }

    public Map<String, Integer> Dijkstra(String startVertexStr) {
        if (!name2Int.containsKey(startVertexStr)) return null;

        int n = arr.length;
        int startIndex = name2Int.get(startVertexStr);
        //tablica odleglsoci
        int[] dist = new int[n];
        //ustalamy wszystkie odleglosci od podanego wierzcholka na max_int (infinity w algorytmie)
        Arrays.fill(dist, Integer.MAX_VALUE);
        //odleglosc od wskazanego wierzcholka jest 0
        dist[startIndex] = 0;

        //tu sledzimy wierzcholki, ktore juz odwiedzilismy
        boolean[] visited = new boolean[n];
        //kolejka priorytetowa dziala jak stack albo kolejka z tym, ze pierwsz yjest element w zaleznosci od warunku jaki narzucimy
        //korzystamy z klasy pomocniczej node, ktora ma index elementu oraz odleglosc
        PriorityQueue<Node> pq = new PriorityQueue<>( Comparator.comparingInt(node -> node.dist) );

        //dodajemy pierwszy element czyli ten od ktorego zaczynamy
        pq.add(new Node(startIndex, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int currentIndex = currentNode.index;

            if (visited[currentIndex]) continue;

            visited[currentIndex] = true;

            for (int i = 0; i < n; i++) {
                if (arr[currentIndex][i] > 0 && !visited[i]) {
                    int newDist = dist[currentIndex] + arr[currentIndex][i];
                    if (newDist < dist[i]) {
                        dist[i] = newDist;
                        pq.add(new Node(i, newDist));
                    }
                }
            }
        }


        Map<String, Integer> result = new HashMap<>();
        for (int i = 0; i < n; i++) {
            result.put(int2Name.get(i), dist[i]);
        }

        return result;
    }

    public int connectedComponents() {
        DisjointSetForest dsf = new DisjointSetForest(arr.length);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] > 0) {
                    dsf.union(i, j);
                }
            }
        }
        return dsf.countSets();
    }
}
