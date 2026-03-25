package com.hubberspot.mockito.junit.mock;

/*

1. BookService is class under test
2. And BookService external dependency is BookRepository.
3. And BookRepository is dependent on database.
4. Goal is to test BookService by using spy doubles. so that we don't need to hit real db.
5. Also spy doubles helps to verify behaviour -> like whenever we are calling addBook() method
   then save() method should be called. But how we can verify that save() method is really called or not?
*/
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

   /* public void addBook(Book book){
        bookRepository.save(book);
    }*/

    public void addBook(Book book){

        if(book.getPrice() > 400){
            return;
        }

        bookRepository.save(book);
    }





}
