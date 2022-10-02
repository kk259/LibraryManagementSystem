package com.example.demo.Library;

import com.example.demo.Library.DTO.BookDto;
import com.example.demo.Library.DTO.BookStockDto;
import com.example.demo.Library.Entity.Book;
import com.example.demo.Library.Entity.BookStock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface Controller {
    @PostMapping
    public String addBooks(@RequestParam int isbn,@RequestParam String title
            ,@RequestParam String author,@RequestParam int noOfCopies);

    @GetMapping
    public List<BookStockDto> getInventory();

    @GetMapping("/books")
    public List<BookDto>  showAllBooks();

    @PutMapping(path="{isbn}")
    public String issueBook(@PathVariable(name = "isbn") int isbn, @RequestParam int customerId);

    @PutMapping(path = "books/return/{bookId}")
    public String returnBook(@PathVariable(name = "bookId") int bookId);

    @DeleteMapping("books/discontinue/{isbn}")
    public String discontinueBook(@PathVariable(name = "isbn") int isbn);
    @PutMapping("books/extend/{bookId}")
    public String extendReturnTime(@PathVariable(name = "bookId") int bookId,@RequestParam int extension);

    @GetMapping("/title/{title}")
    public List<BookStockDto> searchBookByTitle(@PathVariable(name = "title") String bookTitle);
    @GetMapping("/author/{author}")
    public List<BookStockDto> searchBookByAuthor(@PathVariable(name = "author") String author);
}
