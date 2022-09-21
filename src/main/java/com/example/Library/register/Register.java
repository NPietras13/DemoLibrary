package com.example.Library.register;

import com.example.Library.book.Book;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Register {
    @SequenceGenerator(
            name="register_sequence",
            sequenceName = "register_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "register_sequence",
            strategy =  GenerationType.SEQUENCE
    )
    @Id
    private Long id;

    private Long bookId;

    private LocalDate dateOfBorrow;

    private String nameOfClient;

    @ManyToOne
    @JoinColumn(name = "bookId", updatable = false, insertable = false)
    private Book book;


    public Register() {
    }

    public Register(Long id, Long bookId, LocalDate dateOfBorrow, String nameOfClient) {
        this.id = id;
        this.bookId = bookId;
        this.dateOfBorrow = dateOfBorrow;
        this.nameOfClient = nameOfClient;
    }

    public Register(Long bookId, LocalDate dateOfBorrow, String nameOfClient) {
        this.bookId = bookId;
        this.dateOfBorrow = dateOfBorrow;
        this.nameOfClient = nameOfClient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(LocalDate dateOfBorrow) {
        this.dateOfBorrow = dateOfBorrow;
    }

    public String getNameOfClient() {
        return nameOfClient;
    }

    public void setNameOfClient(String nameOfClient) {
        this.nameOfClient = nameOfClient;
    }


    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", dateOfBorrow=" + dateOfBorrow +
                ", nameOfClient='" + nameOfClient + '\'' +
                '}';
    }

}
