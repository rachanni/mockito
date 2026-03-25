package com.hubberspot.mockito.junit.fake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FakeTest {

    /*
    1. We are trying to test BookService using Fake.
    2.

    */
    @Test
    public void testFake(){

//      To create an object of BookService -> we need BookRepository -> Just look at the
//      ctor of BookService.
//      But we don't want to use BookRepository object.
//      why?
//      Because we want to test BookService independently.
//
//        BookService bookService = new BookService();

//      You can see how beautifully we created an object of BookService using FakeBookRepository.
//      We bypass the external dependency to create an instance of BookService.
        BookRepository bookRepository = new FakeBookRepository();
        BookService bookService = new BookService(bookRepository);

        bookService.addBook(new Book("1234", "Mockito In Action", 250, LocalDate.now()));
        bookService.addBook(new Book("51237", "JUnit5 In Action", 200, LocalDate.now()));

//      assertEquals(expectedValue, actualValue)
//
        assertEquals(2, bookService.findNumberOfBooks());
    }
}
