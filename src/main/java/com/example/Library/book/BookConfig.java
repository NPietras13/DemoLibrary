package com.example.Library.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(BookRepository repository){
        return args -> {
            Book book1 = new Book(
                    "book1",
                    "author1",
                    2020
            );

            Book book2 = new Book(
                    "book2",
                    "author2",
                    2018
            );

            Book book3 = new Book(
                    "book3",
                    "author2",
                    2021
            );

        repository.saveAll(List.of(book1,book2,book3));

        };
    }
}
