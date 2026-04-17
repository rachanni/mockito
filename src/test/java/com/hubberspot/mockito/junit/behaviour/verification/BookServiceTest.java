package com.hubberspot.mockito.junit.behaviour.verification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    public void testAddBook(){

        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
        bookService.addBook(book);
//      Mockito.verify(mockObject) helps to test that actually mock object method is called or not.
//      mockObject - bookRepository
//      mock object method - save(book)
//      if mock object method is not called then verify(mockObject) will fail.
//      verify(bookRepository).save(book); is same as verify(bookRepository, times(1)).save(book);
        verify(bookRepository).save(book);
    }

     @Test
    public void testSaveBookWithBookRequestWithGreaterPrice() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
        bookService.addBook(bookRequest);
//      Testing zero interaction with mock object
        verify(bookRepository, times(0)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice1() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 600, LocalDate.now());
//      test case will fail
//        Book book = new Book(null, "Mockito In Action", 10000, LocalDate.now());
        bookService.addBook(bookRequest);
        bookService.addBook(bookRequest);
//      verify() method is not only helping to test number of interaction but it also helps to test correct object
//      is passed.
//      In bookRequest object keep price = 600 and in book object keep price 1000 then test will fail.
        verify(bookRepository, times(2)).save(book);
    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice2() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
        bookService.addBook(bookRequest);
//      instead of using Mockito.times(0) -> better to use Mockito.never()
//        verify(bookRepository, times(0)).save(book);
        verify(bookRepository, never()).save(book);
    }

    @Test
    public void testUpdatePrice(){

        bookService.updatePrice(null, 600);
//      As bookId is null so there should not be interaction with mock object -> bookRepository
//      so to test no interaction scenario we can use verifyNoInteractions(mockObject) method
        verifyNoInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice2(){

        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);
        verify(bookRepository).findBookById("1234");
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice3(){

        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 500);
//      here we are verifying mock object interacted with bookId "1234"
        verify(bookRepository).findBookById("1234");
//      with bookId "89" test will fail
//        verify(bookRepository).findBookById("89");

//        As we know if(book.getPrice() == updatedPrice) -> true ->  only one interaction with bookRepository
//        so testing no second interaction
        verifyNoMoreInteractions(bookRepository);


    }

    @Test
    public void testUpdatePrice4(){

        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 600);
//      here we are verifying mock object interacted with bookId "1234"
        verify(bookRepository).findBookById("1234");
//        verifying second interaction of mock object -> bookRepository.save(book);
        verify(bookRepository).save(book);
//        testing no more interaction after second interaction
        verifyNoMoreInteractions(bookRepository);
    }

    @Test
    public void testUpdatePrice5(){

        Book book = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        when(bookRepository.findBookById("1234")).thenReturn(book);
        bookService.updatePrice("1234", 600);
//      to test the order of interaction of mock object
        InOrder inOrder = Mockito.inOrder(bookRepository);
        inOrder.verify(bookRepository).findBookById("1234");
        inOrder.verify(bookRepository).save(book);

    }


    }

