package com.example.demo.Library;

import com.example.demo.Library.Entity.Book;
import com.example.demo.Library.Entity.BookStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/library")
public class LibraryController {
    public final LibraryService libraryService;
    @Autowired
    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }
    @PostMapping
    public void addBooks(@RequestBody BookStock bookStock){
        libraryService.addBooks(bookStock);
    }

    @GetMapping
    public List<BookStock> getInventory(){
        return libraryService.getInventory();
    }

    @GetMapping("/books")
    public List<Book> showAllBooks(){
        return libraryService.showAllBooks();
    }

    @PutMapping(path="{isbn}")
    public String issueBook(@PathVariable(name = "isbn") int isbn,@RequestParam int customerId){
        if(libraryService.issueBook(isbn,customerId)){
           return "Book issued successfully";
        }
        return "Currently book is not available";
    }

    @PutMapping(path = "books/return/{bookId}")
    public String returnBook(@PathVariable(name = "bookId") int bookId){
        if(libraryService.returnBook(bookId)){
           return "Book returned successfully";
        }
        return  "Book return failure";
    }

    @DeleteMapping("books/discontinue/{isbn}")
    public String discontinueBook(@PathVariable(name = "isbn") int isbn){
        if(libraryService.discontinueBook(isbn)){
            return "Book discontinued successfully";
        }
        return "No Book is present with this isbn";
    }
    @PutMapping("books/extend/{bookId}")
    public String extendReturnTime(@PathVariable(name = "bookId") int bookId,@RequestParam int extension){
        if(libraryService.extendReturnTime(bookId,extension)){
            return "book time extended";
        }
        return "can't extend";
    }

    @GetMapping("/title/{title}")
    public List<BookStock> searchBookByTitle(@PathVariable(name = "title") String bookTitle){
        return libraryService.searchBookByTitle(bookTitle);
    }
    @GetMapping("/author/{author}")
    public List<BookStock> searchBookByAuthor(@PathVariable(name = "author") String author){
        return libraryService.searchBookByAuthor(author);
    }
}





