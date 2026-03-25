package com.hubberspot.mockito.junit.stub;

import org.junit.jupiter.api.Test;

import java.util.List;

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
}
