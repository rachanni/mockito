package com.hubberspot.mockito.junit.annotations.support;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AnnotationsTest {

//  Mocking BookRepository class using annotation -> no need to use static Mockito.mock() method
//  If you are using Mock annotation to mock a class then you need to annotate class with ExtendWith()
//  annotation
//  If you are not annotating class with annotation ExtendWith(MockitoExtension.class) then you
//  will get NullPointerException
    @Mock
    private BookRepository bookRepository; // mocking BookRepository

    @InjectMocks
    private BookService bookService; // class under test

    @Test
    public void demoCreateMocksUsingAnnotations(){

//      Mocking bookRepository
//      BookRepository bookRepository = Mockito.mock(BookRepository.class); -> using Mock annotation
//      BookService bookService = new BookService(bookRepository);  -> using InjectMocks annotation




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
//        then mock bookRepository comes into picture -> And we already defined the behavior of mock bookRepository

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
