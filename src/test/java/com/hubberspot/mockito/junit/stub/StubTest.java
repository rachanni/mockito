package com.hubberspot.mockito.junit.stub;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StubTest {

    @Test
    public void demoStub(){

        BookRepository bookRepository = new BookRepositoryStub();
        BookService bookService = new BookService(bookRepository);

//        here we are calling BookService method -> getNewBooksWithAppliedDiscount() and using assertEquals() to verify business logic
//        written in BookService class
        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

        assertEquals(2, newBooksWithAppliedDiscount.size());
        assertEquals(450, newBooksWithAppliedDiscount.get(0).getPrice());
        assertEquals(360, newBooksWithAppliedDiscount.get(1).getPrice());



    }

    @Test
    public void testDoubleStubWithMockito(){

//      Mocking bookRepository
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);


        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("5127", "Junit5 In Action", 400, LocalDate.now());

        List<Book> newBooks = new ArrayList<>();
        newBooks.add(book1);
        newBooks.add(book2);

//      Defining behaviour for mock object -> bookRepository
//
        Mockito.when(bookRepository.findNewBooks(7)).thenReturn(newBooks);

//        here we are calling BookService method -> getNewBooksWithAppliedDiscount() and using assertEquals() to verify business logic
//        written in BookService class

//        when getNewBooksWithAppliedDiscount() method which is defined in BookService is called
//        then mock bookRepository comes into picture -> And we already defined the behaviour of mock bookRepository

//        Inside getNewBooksWithAppliedDiscount() method -> we have written a line
//        List<Book> newBooks = bookRepository.findNewBooks(days);
//        so here mock bookRepository will be called and mock bookRepository will call findNewBooks() method
//        and it will return a list object newBooks, and we can check discount logic using assert() method
        List<Book> newBooksWithAppliedDiscount = bookService.getNewBooksWithAppliedDiscount(10, 7);

        assertEquals(2, newBooksWithAppliedDiscount.size());
        assertEquals(450, newBooksWithAppliedDiscount.get(0).getPrice());
        assertEquals(360, newBooksWithAppliedDiscount.get(1).getPrice());



    }
}
