package com.hubberspot.mockito.junit.dummy;

/*

1. BookService is class under test
2. And BookService external dependency is BookRepository and EmailService.
3. And we are not using EmailService for any business logic.
4. But EmailService object is required to compile code.
4. so we will use Dummy doubles to test BookService.
*/
public class BookService {

    private BookRepository bookRepository;
    private EmailService emailService;

    public BookService(BookRepository bookRepository, EmailService emailService) {
        this.bookRepository = bookRepository;
        this.emailService = emailService;
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public int findNumberOfBooks(){

        return bookRepository.findAll().size();
    }


//    other methods which uses EmailService



}
