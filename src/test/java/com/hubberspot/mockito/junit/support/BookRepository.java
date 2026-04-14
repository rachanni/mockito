package com.hubberspot.mockito.junit.support;

import java.util.List;

public interface BookRepository {



    List<Book> findNewBooks(int days);
}
