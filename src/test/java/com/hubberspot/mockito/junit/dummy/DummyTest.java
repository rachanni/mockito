package com.hubberspot.mockito.junit.dummy;



import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

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


    @Test
    public void dummyTestDoubleWithMockito(){


//      here we are creating BookRepository mock with the help of Mockito,
//      or simply we are mocking BookRepository
        BookRepository bookRepository = Mockito.mock(BookRepository.class);

//      Mocking EmailService
        EmailService emailService = Mockito.mock(EmailService.class);
//      To create bookService object -> we pass mock bookRepository and mock emailService-> Hence we bypass
//      the need of actual bookRepository and emailService dependency
        BookService bookService = new BookService(bookRepository, emailService);


        Book book1 = new Book("1234", "Mockito In Action", 250, LocalDate.now());
        Book book2 = new Book("51237", "JUnit5 In Action", 200, LocalDate.now());

        Collection<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

//      When you write Mockito.when(bookRepository.findAll()).thenReturn(books);,
//      you're defining the behavior of the mock (i.e., the fake implementation of BookRepository).
//      When the production code under test (bookService.findNumberOfBooks()) calls bookRepository.findAll(), Mockito
//      intercepts that call and returns the value you defined (books).
        Mockito.when(bookRepository.findAll()).thenReturn(books);

//      Goal is to test findNumberOfBooks() method defined in BookService class
//      so here we are calling findNumberOfBooks() method
        assertEquals(2, bookService.findNumberOfBooks());


    }
}
