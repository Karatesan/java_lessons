package org.example;

import java.util.*;

public class Library {
    private List<Book> books;
    private BookService bookService;


    public Library() {
    }

    public Library(BookService bookService) {
        this.books = new ArrayList<>();
        this.bookService = bookService;
    }

    public void addBook(Book book) {
        Optional<Book> bookByIsbn = bookService.findBookByIsbn(book.getIsbn());
        if(bookByIsbn.isEmpty()) books.add(book);
    }

    public void removeBook(int isbn) {
        Optional<Book> book = books.stream().filter(b -> b.getIsbn() == isbn).findFirst();
        book.ifPresent(b->books.remove(b));
    }

    void sortBooksByName(){
        bookService.sortByName(books);
    }



    public List<Book> getAllBooks(){
        return books;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library catalouge = (Library) o;
        return Objects.equals(books, catalouge.books);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(books);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Catalouge{\n");

        for(Book b : books){
            s.append(b);
            s.append(",\n");
        }
        s.append("}");
        return s.toString();
    }
}
