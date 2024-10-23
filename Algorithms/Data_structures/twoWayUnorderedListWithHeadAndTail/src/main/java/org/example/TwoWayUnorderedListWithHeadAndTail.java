package org.example;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E> {


    //================================================================================

    private class Element {
        public Element(E e) {
            this.object = e;
        }

        public Element(E object, Element next, Element prev) {
            this.object = object;
            this.next = next;
            this.prev = prev;
        }

        E object;
        Element next = null;
        Element prev = null;
    }

    //================================================================================

    Element head;
    Element tail;
    // can be realization with the field size or without
    int size = 0;


    private class InnerIterator implements Iterator<E> {
        Element pos;

        // TODO maybe more fields....

        public InnerIterator() {
            //TODO
        }

        @Override
        public boolean hasNext() {
            //TODO
            return false;
        }

        @Override
        public E next() {
            //TODO
            return null;
        }
    }

    //=======================================================================================
    private class InnerListIterator implements ListIterator<E> {
        Element p;
        Element lastReturned;
        int nextIndex=0;

        public InnerListIterator(Element p, Element lastReturned) {
            this.p = p;
            this.lastReturned = lastReturned;
        }

        public InnerListIterator() {
            this.p = head;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return nextIndex<size;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }
//1 p = 2 | last = 1 | index = 1
//2 p = 3 | last = 2 | index =2
//3 p = null | last = 3 | index =3
        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E curr = p.object;
			lastReturned = p;
            p = p.next;
            nextIndex++;
            return curr;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {

            if (!hasPrevious()) throw new NoSuchElementException();
            E curr = lastReturned.object;
            if(p==null) p = tail;
            else p = p.prev;
            lastReturned = p;
            nextIndex--;
            return lastReturned.object;
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
        public void set(E e) {
            p.object = e;

        }
    }

    //=======================================================================================

    public TwoWayUnorderedListWithHeadAndTail() {
        // make a head and a tail
        head = null;
        tail = null;
    }

    @Override
    public boolean add(E e) {
        Element newE = new Element(e);

        if (head == null) {

            head = newE;
            tail = newE;
        } else {

            tail.next = newE;
            newE.prev = tail;
            tail = newE;
        }
		size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        //TODO
    }

    @Override
    public void clear() {
        //TODO
    }

    @Override
    public boolean contains(E element) {
        //TODO
        return false;
    }

    @Override
    public E get(int index) {
        //TODO
        return null;
    }

    @Override
    public E set(int index, E element) {
        //TODO
        return null;
    }

    @Override
    public int indexOf(E element) {
        //TODO
        return -1;
    }

    @Override
    public boolean isEmpty() {
        //TODO

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        //TODO
        return null;
    }

    @Override
    public boolean remove(E e) {
        //TODO
        return true;
    }

    @Override
    public int size() {
        //TODO
        return -1;
    }

    public String toStringReverse() {
        ListIterator<E> iter = new InnerListIterator();
        while (iter.hasNext()) {
			System.out.println(iter.next());
		}
		while(iter.hasPrevious()){
			System.out.println(iter.previous());
		}
        String retStr = "dupa";
        return retStr;
    }

    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
        //TODO
    }
}

