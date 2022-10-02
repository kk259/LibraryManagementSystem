package com.example.demo.Library.Repository;

import com.example.demo.Library.Entity.BookStock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LibraryRepositoryTest {
    @Autowired
    private LibraryRepository underTest;
    @Test
    void existsByIsbn() {
        BookStock bookStock  = new BookStock(1234,"gain","raj",2,0);
        underTest.save(bookStock);
        boolean expected = underTest.existsByIsbn(1234);
        //boolean expected = underTest.existsByIsbn(12345);
        assertThat(expected).isTrue();
    }
}