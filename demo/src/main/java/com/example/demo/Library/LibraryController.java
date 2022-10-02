package com.example.demo.Library;

import com.example.demo.Library.DTO.BookDto;
import com.example.demo.Library.DTO.BookStockDto;
import com.example.demo.Library.Entity.Book;
import com.example.demo.Library.Entity.BookStock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/library")
public class LibraryController implements Controller {
    public final LibraryService libraryService;
    @Autowired
    public LibraryController(LibraryService libraryService){
        this.libraryService = libraryService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public void addBooks(@RequestBody BookStock bookStock){
        libraryService.addBooks(bookStock);
    }

    private BookStockDto convertToBookStockDto(BookStock bookStock) {
        return modelMapper.map(bookStock,BookStockDto.class);
    }
    private BookDto convertToBookDto(Book book) {
        return modelMapper.map(book,BookDto.class);
    }

    @GetMapping
    public List<BookStockDto> getInventory(){
        List<BookStock> bookStocks  = libraryService.getInventory();
        return bookStocks.stream()
                .map(this::convertToBookStockDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/books")
    public List<BookDto>  showAllBooks(){
        List<Book>books= libraryService.showAllBooks();
        return books.stream()
                .map(this::convertToBookDto)
                .collect(Collectors.toList());
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
    public List<BookStockDto> searchBookByTitle(@PathVariable(name = "title") String bookTitle){
        List<BookStock> bookStocks = libraryService.searchBookByTitle(bookTitle);
        return bookStocks.stream()
                .map(this::convertToBookStockDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/author/{author}")
    public List<BookStockDto> searchBookByAuthor(@PathVariable(name = "author") String author){
        List<BookStock> bookStocks = libraryService.searchBookByAuthor(author);
        return bookStocks.stream()
                .map(this::convertToBookStockDto)
                .collect(Collectors.toList());
    }
}





