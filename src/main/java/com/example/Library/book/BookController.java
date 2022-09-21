package com.example.Library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping(path = "{bookId}")
    public Optional<Book> getBook(@PathVariable("bookId") Long bookId){
        return bookService.getBook(bookId);

    }
    @PostMapping
    public void addBook(@RequestBody Book book){
       bookService.addBook(book);

    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
    }

    @PutMapping(path = "{bookId}")
    public void updateBook(@PathVariable("bookId") Long bookId,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String author,
                           @RequestParam(required = false) String yearOfRelease)
    {
        bookService.updateBook(bookId, name, author, yearOfRelease);
    }

}
