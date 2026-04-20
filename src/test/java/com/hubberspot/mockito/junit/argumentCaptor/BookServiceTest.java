package com.hubberspot.mockito.junit.argumentCaptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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

    @Captor
    private ArgumentCaptor<Book> bookArgumentCaptor;


     @Test
    public void testSaveBoo() {
        BookRequest bookRequest = new BookRequest("Mockito In Action", 600, LocalDate.now());
//        Book book = new Book(null, "Mockito In Action", 500, LocalDate.now());
//        ArgumentCaptor<Book> bookArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        bookService.addBook(bookRequest);
        verify(bookRepository, times(1)).save(bookArgumentCaptor.capture());
        Book book = bookArgumentCaptor.getValue();
        assertEquals("Mockito In Action", book.getTitle());
    }


}

