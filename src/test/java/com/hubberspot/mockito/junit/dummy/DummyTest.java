package com.hubberspot.mockito.junit.dummy;



import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DummyTest {

    @Test
    public void demoDummy(){

        BookRepository bookRepository = new FakeBookRepository();

//        here BookService constructor requires two parameters -> 1. BookRepository  2. EmailService
//        but if you look at the code of BookService -> It is not used for business logic.
//        EmailService is needed just for code compilation.
//        So we will create DummyEmailService which will implement EmailService.
//        And we will use DummyEmailService object.

//        BookService bookService = new BookService(bookRepository);

        EmailService emailService = new DummyEmailService();
        BookService bookService = new BookService(bookRepository, emailService);
        bookService.addBook(new Book("1234", "Mockito In Action", 250, LocalDate.now()));
        bookService.addBook(new Book("51237", "JUnit5 In Action", 200, LocalDate.now()));

//      assertEquals(expectedValue, actualValue)
//
        assertEquals(1, bookService.findNumberOfBooks());
    }
}
