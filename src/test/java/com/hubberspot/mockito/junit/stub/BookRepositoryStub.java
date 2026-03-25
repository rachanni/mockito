package com.hubberspot.mockito.junit.stub;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryStub implements BookRepository {


    /*
    * Instead of hitting real db to get books. We hardcoded few books and will test discount
    * logic using these hardcoded books.
    * */
    @Override
    public List<Book> findNewBooks(int days) {

        List<Book> newBooks = new ArrayList<>();
        Book book1 = new Book("1234", "Mockito In Action", 500, LocalDate.now());
        Book book2 = new Book("5127", "Junit5 In Action", 400, LocalDate.now());

        newBooks.add(book1);
        newBooks.add(book2);

        return newBooks;
    }
}
