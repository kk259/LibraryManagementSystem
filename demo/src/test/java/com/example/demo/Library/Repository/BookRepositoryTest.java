package com.example.demo.Library.Repository;

import com.example.demo.Library.Entity.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository underTest;
    @Test
    void itShouldFindBookByIsbn() {
        Book book = new Book(10,25000,null,0,0);
        underTest.save(book);
        assertThat(underTest.findBookByIsbn(25000)).isNotNull();
        /*Book book1 = new Book(10,25000,null,0,0);
        Book book2 = new Book(20,25000,null,0,0);
        underTest.save(book1);
        underTest.save(book2);
        List<Book> books = underTest.findBookByIsbn(25000);
        //assertThat(books).isEqualTo(List.of(book1,book2));*/

    }
}