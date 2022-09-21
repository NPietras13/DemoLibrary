package com.example.Library.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.name=?1 AND b.author=?2")
    List<Book> findBooksByNameAndAuthor(String name, String author);

    @Query("SELECT b FROM Book b WHERE b.name=?1")
    Optional<Book> findBookByName(String name);

    @Query("SELECT b FROM Book b WHERE b.author=?1")
    Optional<Book> findBookByAuthor(String author);
}
