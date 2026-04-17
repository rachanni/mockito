package com.hubberspot.mockito.junit.behaviour.verification;


public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }





    public void addBook(Book book){

        /*if(book.getPrice() <= 500){
            return;
        }*/
//
        bookRepository.save(book);
    }

    public void addBook(BookRequest bookRequest){

        if(bookRequest.getPrice() <= 500){
            return;
        }
        Book book = new Book();
        book.setTitle(bookRequest.getTitle());
        book.setPrice(bookRequest.getPrice());
        book.setPublishedDate(bookRequest.getPublishedDate());

        bookRepository.save(book);
    }

    public void updatePrice(String bookId, int updatedPrice){
//      In case of bookId is null then bookRepository object should not come into the picture.
        if(bookId == null){
            return;
        }
//      first interaction with mock object
        Book book = bookRepository.findBookById(bookId);
//      if(book.getPrice() == updatedPrice) -> true ->  only one interaction with bookRepository
//      if(book.getPrice() == updatedPrice) -> false -> then two interaction should happen with bookRepository
        if(book.getPrice() == updatedPrice){
            return;
        }
        book.setPrice(updatedPrice);
//      second interaction with mock object
        bookRepository.save(book);
    }



}
