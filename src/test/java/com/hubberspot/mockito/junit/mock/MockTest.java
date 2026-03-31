package com.hubberspot.mockito.junit.mock;



import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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

    @Test
    public void mockTestDoubleWithMockito(){

//      Mocking BookRepository
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        BookService bookService = new BookService(bookRepository);

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("5127", "Junit5 In Action", 400, LocalDate.now());


        bookService.addBook(book1); // return
        bookService.addBook(book2); // save will be called

//      for book2 save() will be called -> test pass
        Mockito.verify(bookRepository).save(book2);

//        for book1 save() will not be called
//        Mockito.verify(bookRepository).save(book1);

//      for book2 save() will be called 1 times
        Mockito.verify(bookRepository, Mockito.times(1)).save(book2);
//      for book1 save() will be called 0 times
        Mockito.verify(bookRepository, Mockito.times(0)).save(book1);

    }
}
