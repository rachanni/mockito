package com.hubberspot.mockito.junit.fake;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FakeBookRepository implements BookRepository {


//  FakeBookRepository will not go to real db.
//  We can use in memory db, HashMap or List

//  Here bookStore is acting as a lighter form of db.
    Map<String, Book> bookStore = new HashMap<>();




    @Override
    public void save(Book book) {

        bookStore.put(book.getBookId(), book);
    }

    @Override
    public Collection<Book> findAll() {
        return bookStore.values();
    }
}
