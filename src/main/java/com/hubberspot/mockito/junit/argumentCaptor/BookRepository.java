package com.hubberspot.mockito.junit.argumentCaptor;

public interface BookRepository {

    void save(Book book);

    Book findBookById(String bookId);
}
