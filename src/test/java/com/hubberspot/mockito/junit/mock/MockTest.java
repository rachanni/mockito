package com.hubberspot.mockito.junit.mock;



import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockTest {

    @Test
    public void demoMock(){

        BookRepositoryMock bookRepositoryMock = new BookRepositoryMock();
        BookService bookService = new BookService(bookRepositoryMock);

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("5127", "Junit5 In Action", 400, LocalDate.now());


        bookService.addBook(book1);
        bookService.addBook(book2);

        bookRepositoryMock.verify(book2, 1);





    }
}
