package com.example.demo.Library;

import com.example.demo.Library.Entity.Book;
import com.example.demo.Library.Entity.BookStock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface Controller {
    @PostMapping
    public void addBooks(@RequestBody BookStock bookStock);
    @GetMapping
    public ResponseEntity<List<BookStock>> getInventory();

    @GetMapping("/books")
    public ResponseEntity<List<Book>>  showAllBooks();

    @PutMapping(path="{isbn}")
    public String issueBook(@PathVariable(name = "isbn") int isbn, @RequestParam int customerId);

    @PutMapping(path = "books/return/{bookId}")
    public String returnBook(@PathVariable(name = "bookId") int bookId);

    @DeleteMapping("books/discontinue/{isbn}")
    public String discontinueBook(@PathVariable(name = "isbn") int isbn);
    @PutMapping("books/extend/{bookId}")
    public String extendReturnTime(@PathVariable(name = "bookId") int bookId,@RequestParam int extension);

    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookStock>> searchBookByTitle(@PathVariable(name = "title") String bookTitle);
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookStock>> searchBookByAuthor(@PathVariable(name = "author") String author);
}
