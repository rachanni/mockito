package com.hubberspot.mockito.junit.fake;

import java.util.Collection;

public interface BookRepository {

    void save(Book book);
    Collection<Book> findAll();
}
