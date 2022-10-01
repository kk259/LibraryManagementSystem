package com.example.demo.Library;

import com.example.demo.Library.Entity.Book;
import com.example.demo.Library.Entity.BookStock;
import com.example.demo.Library.Repository.LibraryRepository;
import com.example.demo.Library.Repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class LibraryConfig {
    @Bean
    CommandLineRunner commandLineRunner(LibraryRepository bookInformationRepository, BookRepository bookRepository) {
        return args -> {
            BookStock bookStock = new BookStock(1234567810, "mindset","james",1,0);
            Book book = new Book(1234567810);
            bookInformationRepository.save(bookStock);
            bookRepository.save(book);
        };
    }
}
