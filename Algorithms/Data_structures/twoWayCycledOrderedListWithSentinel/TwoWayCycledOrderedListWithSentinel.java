import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayCycledOrderedListWithSentinel<E extends Comparable<E>> implements IList<E> {

    private class Element {
        private E object;
        private Element next;
        private Element prev;

        public Element(E e) {
            this.object = e;
            next = null;
            prev = null;
        }

        public Element(E e, Element next, Element prev) {
            this.object = e;
            this.next = next;
            this.prev = prev;
        }

        // add element e after this
        public void addAfter(Element elem) {
            elem.prev = this;
            elem.next = this.next;
            this.next.prev = elem;
            this.next = elem;
        }

        // assert it is NOT a sentinel
        public void remove() {
            this.next.prev = this.prev;
            this.prev.next = this.next;
        }
    }

    Element sentinel;
    int size;

    private class InnerIterator implements Iterator<E> {
        Element current = sentinel;
        public InnerIterator() {
            //TODO
        }

        @Override
        public boolean hasNext() {
            return current.next!=sentinel;
        }

        @Override
        public E next() {
            current=current.next;
            return current.object;
        }
    }

    private class InnerListIterator implements ListIterator<E> {
        boolean wasNext=false;
        boolean wasPrevious=false;
        Element _current=sentinel;

        public InnerListIterator() {
        }

        @Override
        public boolean hasNext() {
            return _current.next!=sentinel;
        }

        @Override
        public E next() {
            wasNext=true;
            wasPrevious=false;
            _current=_current.next;
            return _current.object;
        }

        @Override
        public void add(E arg0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasPrevious() {
            return _current!=sentinel;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            wasNext=false;
            wasPrevious=true;
            E retValue=_current.object;
            _current=_current.prev;
            return retValue;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E arg0) {
            throw new UnsupportedOperationException();
        }
    }

    public TwoWayCycledOrderedListWithSentinel() {
        sentinel = new Element(null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        Element newElem = new Element(e);
        Element elem = sentinel.next;
        while(elem!=sentinel && e.compareTo(elem.object)>=0){
            elem = elem.next;
        }
        elem.prev.addAfter(newElem);
        return true;
    }

    private Element getElement(int index) {
        if (index < 0) throw new IndexOutOfBoundsException();
        Element elem = sentinel.next;
        int counter = 0;
        while (elem != sentinel && counter < index) {
            counter++;
            elem = elem.next;
        }
        if (elem == sentinel)
            throw new IndexOutOfBoundsException();
        return elem;
    }

    private Element getElement(E obj) {
        Element elem = sentinel.next;
        while (elem != sentinel && !obj.equals(elem.object)) {
            elem = elem.next;
        }
        if (elem == sentinel)
            return null;
        return elem;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void clear() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element)!=-1;
    }

    @Override
    public E get(int index) {
        Element elem=getElement(index);
        return elem.object;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(E element) {
        Element elem=sentinel.next;
        int counter=0;
        while(elem!=sentinel && !elem.object.equals(element)){
            counter++;
            elem=elem.next;}
        if(elem==sentinel)
            return -1;
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next==sentinel;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new InnerListIterator();
    }

    @Override
    public E remove(int index) {
        Element elem=getElement(index);
        elem.remove();
        return elem.object;
    }

    @Override
    public boolean remove(E e) {
        Element elem=getElement(e);
        if(elem==null) return false;
        elem.remove();
        return true;
    }

    @Override
    public int size() {
        Element elem=sentinel.next;
        int counter=0;
        while(elem!=sentinel){
            counter++;
            elem=elem.next;}
        return counter;
    }

    //@SuppressWarnings("unchecked")
    public void add(TwoWayCycledOrderedListWithSentinel<E> other) {
        if(this == other) return;

        ListIterator<E> iter = other.listIterator();
        while(iter.hasNext()){
            this.add(iter.next());
        }
        other.clear();

//        for (E e : other) {
//            this.add(e);
//        }

    }

    //@SuppressWarnings({ "unchecked", "rawtypes" })
    public void removeAll(E e) {
        if(isEmpty()) return;
        Element element = sentinel.next;
        while(element!=sentinel){
            if(element.object.equals(e)){
                Element next = element.next;
                element.remove();
                element = next;
            }
            element = element.next;
        }


//        Element element = sentinel.next;
//        while(element!=sentinel){
//            if(element.object.equals(e)){
//                element.remove();
//            }
//            element = element.next;
//        }
    }

}

