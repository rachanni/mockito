package com.hubberspot.mockito.junit.stubbing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    public void testcalculateTotalCost(){
        
        List<String> bookIds = new ArrayList<>();
        bookIds.add("1234");
        bookIds.add("1235");

        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("1235", "Junit5 In Action", 400, LocalDate.now());


//        Approach -1
//        Inside when() mock object method call is completed -> when() is taking mock object method call as argument
//        Inside thenReturn() stubbed response is passed -> thenReturn() is taking stubbed object as an argument
//        when(mock object method call).thenReturn(stubbed response)
//        this when() belongs to Mockito class
//        when(bookRepository.findBookByBookId("1234")).thenReturn(book1);
//        when(bookRepository.findBookByBookId("1235")).thenReturn(book2);

//        Approach - 2
//        doReturn() -> is taking stubbed object as an argument
//        when() -> is taking only mock object as an argument
//        when().mock object method -> when() is calling mock object method
//        doReturn(stubbed response).when(mock object).mock object method
//        this when() belongs to Stubber interface
        doReturn(book1).when(bookRepository).findBookByBookId("1234");
        doReturn(book2).when(bookRepository).findBookByBookId("1235");

//        what will happen if we club approach - 1 and approach - 2
//        org.mockito.exceptions.misusing.UnfinishedStubbingException:
//        doReturn(book1).when(bookRepository.findBookByBookId("1234"));
//        doReturn(book2).when(bookRepository.findBookByBookId("1235"));




        int actualCost = bookService.calculateTotalCost(bookIds);
        assertEquals(900, actualCost);
    }


    @Test
    public void testAddBook(){

        Book book1 = new Book(null, "Mockito In Action", 500, LocalDate.now());
//      here mock object -> bookRepository is calling save() method and save() method return type is void.
//      so instead of using thenReturn() -> we are using doNothing()
        doNothing().when(bookRepository).save(book1); // ==
        bookService.addBook(book1);

    }

    /*
    *
    * */

    @Test
    public void testAddBookWithBookRequest(){

        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now()); // Book@5a6d5a8f
//      here mock object is calling save() method on book object having hashCode Book@5a6d5a8f but the moment
//      bookService.addBook(bookRequest); executed then addBook(BookRequest bookRequest) of BookService will be
//      called. And there also we are creating a new book object having hashCode Book@450794b4
        /*
        * In other word we are using save(book) method two times
        * 1. doNothing().when(bookRepository).save(book); -> here in test method
        * 2. bookRepository.save(book); -> Inside BookService addBook(BookRequest bookRequest) method
        *
        * so by default mockito use equals() method to compare these 2 object. If we don't override equals() method
        * then Object class equals method will be used which use reference comparison.
        * And obviously we are using two different book object. so we are getting
        * org.mockito.exceptions.misusing.PotentialStubbingProblem
        * so to avoid this exception -> override equals() method based on fields which satisfy the real condition.
        * Real condition means if two book have same title, price and publishDate then we can say these 2 books are same
        * irrespective of different bookId.
        * */
        doNothing().when(bookRepository).save(book);
        bookService.addBook(bookRequest);

    }

    @Test
    public void testSaveBookWithBookRequestWithGreaterPrice() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 500, LocalDate.now());
        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
//		doNothing().when(bookRepository).save(book);
//      this test case passed so we can assume service class addBook(BookRequest bookRequest) method working successfully.
//      But in reality it does not even goes till end and terminate after executing if statement. so mock object method
//      is not executed at all.
//      In other words, there is no interaction with mock object method at all.
        bookService.addBook(bookRequest);
    }
}
