package org.example;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> findBookByIsbn(int isbn);
    boolean isBookAvailable(int isbn);
    Optional<Book> findByTitle(String title);
    void sortByName(List<Book> books);

}
