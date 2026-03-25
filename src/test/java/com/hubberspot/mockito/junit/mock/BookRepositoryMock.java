package com.hubberspot.mockito.junit.mock;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookRepositoryMock implements BookRepository {

//    number of times save() method called
    int saveCalled = 0;

//    we are assigning last added book to this variable.
    Book lastAddedBook = null;

//    we are providing implementation to save() method used in BookService class.
    @Override
    public void save(Book book) {

        saveCalled++;
        lastAddedBook = book;

    }

    public void verify(Book book, int times){

        assertEquals(times, saveCalled);
        assertEquals(book, lastAddedBook);
    }


}
