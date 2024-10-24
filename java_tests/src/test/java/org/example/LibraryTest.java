package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatalogueTest {

    @Mock
    BookService bookService;
    @InjectMocks
    Library c;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        //or @ExtendWith(MockitoExtension.class)
    }

    @Test
    public void getAllBooks_ReturnsAListOfLengthZero_whenCatalogueHasHadNoBooksAdded() {
        //arrange
        //act
        List<Book> allBooks = c.getAllBooks();
        //assert
        assertEquals(0, allBooks.size());
    }


    @Test
    public void testAddBook() {
        // Arrange
        Book book = new Book("Effective Java", "Joshua Bloch", 123456, 45.0, 416);
        when(bookService.findBookByIsbn(123456)).thenReturn(Optional.empty());
        //when(bookService.findBookByIsbn(123456)).thenReturn(book);

        // Act
        c.addBook(book);

        // Assert
        List<Book> allBooks = c.getAllBooks();
        assertEquals(1, allBooks.size());
        assertEquals(book, allBooks.get(0));

        // Verify interactions
        //verify(bookService).isBookAvailable(123456);
//        verify(bookService).findBookByIsbn(123456);
    }

    @Test
    public void testSort(){
        //arrange
        Book book = new Book("The Catcher in the Rye", "J.D. Salinger", 123456, 9.99, 277);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 234567, 7.99, 336);
        //act
        c.addBook(book);
        c.addBook(book2);
        //Assert
        c.sortBooksByName();

        verify(bookService,times(2)).sortByName(c.getAllBooks());

    }


    @Test
    public void getAllBooks_ReturnsAListOfLengthOne_whenCatalogueHasHadOneBookAdded() {

        //arrange
        Book book = new Book("The Catcher in the Rye", "J.D. Salinger", 123456, 9.99, 277);
        //act
        c.addBook(book);
        List<Book> allBooks = c.getAllBooks();
        //assert
        assertEquals(1, allBooks.size());
    }

    @Test
    public void getAllBooks_ReturnsAListOfLengthTwo_whenCatalogueHasHadTwoBooksAdded() {
        //arrange
        Book book = new Book("The Catcher in the Rye", "J.D. Salinger", 123456, 9.99, 277);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 234567, 7.99, 336);
        //act
        c.addBook(book);
        c.addBook(book2);
        List<Book> allBooks = c.getAllBooks();
        //assert
        assertEquals(2, allBooks.size());
    }

    @Test
    public void getAllBooks_returnsAListThatContainsAddedBook(){
        //arrange

        Book book = new Book("To Kill a Mockingbird", "Harper Lee", 234567, 7.99, 336);
        //act
        c.addBook(book);
        List<Book> allBooks = c.getAllBooks();
        //assert
        assertEquals(book, allBooks.getFirst());
    }

}


