public class BST<T extends Comparable<T>> {
    private class Node {
        T value;
        Node left, right, parent;

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node left, Node right, Node parent) {
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private Node root = null;
    private int size = 0;

    public BST() {
    }

    public T getElement(T toFind) {
        Node current = root;
        while (current != null) {
            int cmp = toFind.compareTo(current.value);
            if (cmp == 0) {
                return current.value;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public T successor(T elem) {
        //szukamy noda
        Node current = getNode(root, elem);

        if (current == null) {
            return null;
        }

        //jezeli nod ma prawe dziecko to sukcesorem jest najmniejsza wartosc z prawego drzewka (lecimy w lewo)
        if (current.right != null) {
            return getMin(current.right).value;
        }
        Node parent = current.parent;
        //lecimy do gory i dopoki parent jest != null i nasz nod jest prawym dzieckiem idziemy do gory
        //do momentu az naz current bedzie lewym dzieckiem
        while (parent != null && current == parent.right) {
            current = parent;
            parent = parent.parent;
        }
        return parent != null ? parent.value : null;
    }

    public String toStringInOrder() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString();
    }

    private void inOrderTraversal(Node node, StringBuilder sb) {
        if (node != null) {
            inOrderTraversal(node.left, sb);
            sb.append(node.value).append(" ");
            inOrderTraversal(node.right, sb);
        }
    }

    public String toStringPreOrder() {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString();
    }

    private void preOrderTraversal(Node node, StringBuilder sb) {
        if (node != null) {
            sb.append(node.value).append(" ");
            preOrderTraversal(node.left, sb);
            preOrderTraversal(node.right, sb);
        }
    }

    public String toStringPostOrder() {
        StringBuilder sb = new StringBuilder();
        postOrderTraversal(root, sb);
        return sb.toString();
    }

    private void postOrderTraversal(Node node, StringBuilder sb) {
        if (node != null) {
            postOrderTraversal(node.left, sb);
            postOrderTraversal(node.right, sb);
            sb.append(node.value).append(" ");
        }
    }

    public boolean add(T elem) {

        if (root == null) {
            root = new Node(elem);
            size++;
            return true;
        }

        Node current = root;
        Node parent = null;
        //idziemy w dol drzewka, sprawdzajac compare to czy idziemy w lewo czy w prawo, ustawiajac po drodze rodzica
        //do momentu gdy dojdziemy do konca drzewka
        while (current != null) {
            int cmp = elem.compareTo(current.value);
            if (cmp == 0) {
                return false; // Duplicate value
            }
            parent = current;
            if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        //sprawdzamy czy mamy dodac jako lewe czy prawe dziecko
        Node newNode = new Node(elem);
        newNode.parent = parent;
        if (elem.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
        return true;
    }

    public T remove(T value) {
        Node nodeToRemove = getNode(root, value);
        if (nodeToRemove == null) {
            return null;
        }
        T removedValue = nodeToRemove.value;
        removeNode(nodeToRemove);
        size--;
        return removedValue;
    }

    private Node getNode(Node node, T value) {
        while (node != null) {
            int cmp = value.compareTo(node.value);
            if (cmp == 0) {
                return node;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    private void removeNode(Node node) {
        // jezeli nod jest lisciem (ostatni) to zmieniamy odpowiedniego rodzica dziecko
        if (node.left == null && node.right == null) {
            if (node == root) {
                root = null;
            } else if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
        //jezeli ma dwoje dzieci
        else if (node.left != null && node.right != null) {
            Node successor = getMin(node.right);
            node.value = successor.value;
            removeNode(successor);
        } else { // One child
            Node child = (node.left != null) ? node.left : node.right;
            if (node == root) {
                root = child;
                child.parent = null;
            } else if (node == node.parent.left) {
                node.parent.left = child;
                child.parent = node.parent;
            } else {
                node.parent.right = child;
                child.parent = node.parent;
            }
        }
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }
}
