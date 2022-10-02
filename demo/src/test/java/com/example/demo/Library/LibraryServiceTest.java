package com.example.demo.Library;

import com.example.demo.Library.Repository.BookRepository;
import com.example.demo.Library.Repository.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;
    @Mock
    private BookRepository bookRepository;
    private LibraryService underTest;

    @BeforeEach
    void setUp(){
        underTest = new LibraryService(libraryRepository,bookRepository);
    }

    @Test
    void getInventory() {
        underTest.getInventory();
        verify(libraryRepository).findAll();
    }
}