package com.example.demo.Library.Repository;

import com.example.demo.Library.Entity.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<BookStock,Integer> {

    @Query("select case when count(b)>0 then true else false end from Library b where b.isbn=?1")
    public boolean existsByIsbn(int isbn);
    @Query
    public BookStock findByIsbn(int isbn);
    @Query("select b from Library b where b.BookAuthor = ?1")
    public List<BookStock> findByBookAuthor(String BookAuthor);
    @Query("select b from Library b where b.BookTitle = ?1")
    public List<BookStock> findByBookTitle(String BookTitle);

}
