package com.hubberspot.mockito.junit.fake;

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

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public int findNumberOfBooks(){

        return bookRepository.findAll().size();
    }

}
