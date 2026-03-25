package com.hubberspot.mockito.junit.dummy;

import java.util.Collection;

public interface BookRepository {

    void save(Book book);
    Collection<Book> findAll();
}
