package com.hubberspot.mockito.junit.spy;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpyTest {

    @Test
    public void demoSpy() {

        BookRepositorySpy bookRepositorySpy = new BookRepositorySpy();
        BookService bookService = new BookService(bookRepositorySpy);

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("5127", "Junit5 In Action", 400, LocalDate.now());


//        bookService.addBook(book1);
//        bookService.addBook(book2);

//        assertEquals(2, bookRepositorySpy.timesCalled());
//        assertTrue(bookRepositorySpy.calledWith(book2));


        bookService.addBook(book1);
        assertEquals(0, bookRepositorySpy.timesCalled());

        bookService.addBook(book2);
        assertEquals(1, bookRepositorySpy.timesCalled());


    }

    @Test
    public void testDoubleSpyWithMockito() {

//      Mocking bookRepository
        BookRepository bookRepository = Mockito.spy(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("5127", "Junit5 In Action", 400, LocalDate.now());


        bookService.addBook(book1);
        bookService.addBook(book2);

        Mockito.verify(bookRepository, Mockito.times(1)).save(book2);
        Mockito.verify(bookRepository, Mockito.times(0)).save(book1);


    }

}
