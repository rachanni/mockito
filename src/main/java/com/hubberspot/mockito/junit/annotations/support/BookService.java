package com.hubberspot.mockito.junit.annotations.support;

import java.util.List;

/*

1. BookService is class under test
2. And BookService external dependency is BookRepository.
3. And BookRepository is dependent on database.
4. Goal is to test BookService by using fake doubles. so that we don't need to hit real db.
*/
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    /*
    * 1. Here we are fetching books from db then applying business logic on books.
    * 2. Here Business logic is following -
    *    Apply 10 % discount on all books published within the last 7 days
    * */


    /* here goal is to test this method logic.*/
    public List<Book> getNewBooksWithAppliedDiscount(int discountRate, int days){

        List<Book> newBooks = bookRepository.findNewBooks(days);
        // cost price = 500 -> after 10 % discount -> 500 - 50 = 450
        for(Book book : newBooks){

            int price = book.getPrice();
            int newPrice = price - (discountRate * price / 100);
            book.setPrice(newPrice);
        }
        return newBooks;
    }



}
