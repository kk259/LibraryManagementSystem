package com.example.demo.Library.Repository;

import com.example.demo.Library.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query("select b from Book b where b.isbn = ?1")
    List<Book> findBookByIsbn(int isbn);

    @Query
    Book findBookByBookId(int bookId);
}
