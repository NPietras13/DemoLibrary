package com.example.Library.book;

import javax.persistence.*;

@Entity
@Table
public class Book {
    @SequenceGenerator(
            name="book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "book_sequence",
            strategy =  GenerationType.SEQUENCE
    )
    @Id
    private Long id;

    private String name;

    private String author;

    private Integer yearOfRelease;

    public Book() {

    }

    public Book(Long id, String name, String author, Integer yearOfRelease) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfRelease = yearOfRelease;
    }

    public Book(String name, String author, Integer yearOfRelease) {
        this.name = name;
        this.author = author;
        this.yearOfRelease = yearOfRelease;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(Integer yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", yearOfRelease=" + yearOfRelease +
                '}';
    }
}
