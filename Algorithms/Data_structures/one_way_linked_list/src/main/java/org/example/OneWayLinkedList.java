package org.example;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object=e;
        }
        E object;
        Element next=null;
    }
//element wskazujacy pierwszy ale nie wlicza sie
    Element sentinel;

    private class InnerIterator implements Iterator<E>{

        Element current;
        public InnerIterator() {
            current = sentinel.next;
        }
        @Override
        public boolean hasNext() {
          return current!=null;
        }

        @Override
        public E next() {
         E value = current.object;
         current = current.next;
         return value;
        }
    }

    public OneWayLinkedList() {
        sentinel = new Element(null);
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
    public boolean add(E e) {
        Element current = sentinel;
        while(current.next!=null){
            current = current.next;
        }
        current.next = new Element(e);
        return true;
    }

    @Override
    public void add(int index, E element) throws NoSuchElementException {
        Element newElement = new Element(element);
        if(index==0) {
            newElement.next = sentinel.next;
            sentinel.next = newElement;
            return;
        }

        Element elementBeforeIndex = getElement(index-1);
        newElement.next = elementBeforeIndex.next;
        elementBeforeIndex.next = newElement;
    }
    private Element getElement(int index) throws NoSuchElementException{
        if(index<0) throw new NoSuchElementException();
        Element current = sentinel.next;
        while(index>0 && current!=null){
            index--;
            current = current.next;
        }
        if(current == null) throw new NoSuchElementException();
        return current;
    }


    @Override
    public void clear() {
      sentinel.next = null;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element)!=-1;
    }

    @Override
    public E get(int index) throws NoSuchElementException {
//        if(index<0) throw new NoSuchElementException();
//        Element current = sentinel.next;
//        while(index>0 && current!=null){
//            index--;
//            current = current.next;
//        }
//        if(current==null) throw new NoSuchElementException();
//        return current.object;

        Element elementAtIndex = getElement(index);
        return elementAtIndex.object;
    }



    @Override
    public E set(int index, E element) throws NoSuchElementException {
        if(index<0) throw new NoSuchElementException();
        Element elemAtPos = getElement(index);
        E oldData = elemAtPos.object;
        elemAtPos.object = element;
        return oldData;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;
        Element current = sentinel.next;
        while(current!=null){
            if(current.object.equals(element)) return index;
            index++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next==null;
    }

    @Override
    public E remove(int index) throws NoSuchElementException {
        if(sentinel.next == null) throw new NoSuchElementException();
        Element elementBeforeIndex = getElement(index-1);
        Element elementToDelete = getElement(index);
        E value = elementToDelete.object;
        elementBeforeIndex.next = elementToDelete.next;
        return value;
    }

    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if(index<0) return false;
        remove(index);
        return true;
    }

    @Override
    public int size() {
        //=============================================
        Element current = sentinel.next;
        int size = 0;
        while(current!=null){
            size++;
            current = current.next;
        }
        return size;
        //=============================================
    }

}

