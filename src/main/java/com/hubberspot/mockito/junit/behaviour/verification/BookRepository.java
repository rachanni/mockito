package com.hubberspot.mockito.junit.behaviour.verification;

import java.util.List;

public interface BookRepository {




    void save(Book book);

    Book findBookById(String bookId);
}
