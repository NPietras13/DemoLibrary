package com.example.Library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public List<Book> getBooks(){
       return bookRepository.findAll();
    }

    public Optional<Book> getBook(Long bookId){


        Optional<Book> optionalBook= bookRepository.findById(bookId);
        if(optionalBook.isEmpty())throw new IllegalStateException("book with id: "+bookId+" does not exist");
        return optionalBook;

    }

    public void addBook(Book book){

        if(book.getId()!=null){
        Boolean exists = bookRepository.existsById(book.getId());
        if(exists)throw new IllegalStateException("id: "+book.getId()+" is already taken");}


        if(book.getName()==null || book.getName().length()==0)throw new IllegalStateException("name of book is missing");

        if(book.getAuthor()==null || book.getAuthor().length()==0)throw new IllegalStateException("name of author is missing");

        if(book.getYearOfRelease()==null)throw new IllegalStateException("year of release is missing");

        int year = Year.now().getValue();
        if(book.getYearOfRelease()<0 || book.getYearOfRelease()>year)throw new IllegalStateException("year of release is invalid");


        if(!bookRepository.findBooksByNameAndAuthor(book.getName(), book.getAuthor()).isEmpty()) {
            throw new IllegalStateException("this book is already in database");
        }

        bookRepository.save(book);

    }

    public void deleteBook(Long bookId){
        if(!bookRepository.existsById(bookId))throw new IllegalStateException("book with id: "+bookId+" does not exist");

        bookRepository.deleteById(bookId);
    }


    @Transactional
    public void updateBook(Long bookId, String name, String author, String yearOfRelease) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->new IllegalStateException("book with id: "+bookId+" does not exist"));

        if(name!=null && author!=null && !bookRepository.findBooksByNameAndAuthor(name, author).isEmpty())
            throw new IllegalStateException("this book is already in database");

        if(name!=null && author==null && !bookRepository.findBooksByNameAndAuthor(name, book.getAuthor()).isEmpty())
            throw new IllegalStateException("this book is already in database");

        if(name==null && author!=null && !bookRepository.findBooksByNameAndAuthor(book.getName(), author).isEmpty())
            throw new IllegalStateException("this book is already in database");


        if(name!=null && name.length()>0 && !Objects.equals(name, book.getName() ))
            book.setName(name);

        if(author!=null && author.length()>0 && !Objects.equals(author, book.getAuthor() ))
            book.setAuthor(author);


        Integer yearOfReleaseInt=Integer.valueOf(yearOfRelease);

        if(yearOfReleaseInt!=null) {
            if(yearOfReleaseInt<0 || yearOfReleaseInt>Year.now().getValue())
                throw new IllegalStateException("year of release is invalid");
            book.setYearOfRelease(yearOfReleaseInt);
        }

    }
}
