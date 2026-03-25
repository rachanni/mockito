package com.hubberspot.mockito.junit.spy;

public class BookRepositorySpy implements BookRepository {

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

    public int timesCalled(){

        return saveCalled;
    }

    public boolean calledWith(Book book){

        return lastAddedBook.equals(book);
    }
}
