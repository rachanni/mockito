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
    public void testSaveBook(){

        Book book1 = new Book(null, "Mockito In Action", 500, LocalDate.now());
        doNothing().when(bookRepository).save(book1);
        bookService.addBook(book1);

    }
}
