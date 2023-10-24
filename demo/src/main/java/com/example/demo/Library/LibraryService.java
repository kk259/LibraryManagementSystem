package com.example.demo.Library;

import com.example.demo.Library.Entity.Book;
import com.example.demo.Library.Entity.BookStock;
import com.example.demo.Library.Repository.LibraryRepository;
import com.example.demo.Library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class LibraryService {
    public final LibraryRepository libraryRepository;
    public final BookRepository bookRepository;
    @Autowired
    public LibraryService(LibraryRepository libraryRepository, BookRepository bookRepository){
        this.bookRepository = bookRepository;
        this.libraryRepository = libraryRepository;
    }
    @Transactional
    public void changeQuantity(BookStock bookStock, int quantity){
        bookStock.setTotalCopies(quantity);
    }
    // If the same book is already present in library then increasing it's stock otherwise
    // adding new stock of the book in library
    public void addBooks(BookStock bookStock) {
        int isbn = bookStock.getIsbn();
        int n = bookStock.getTotalCopies();
        if(!libraryRepository.existsByIsbn(isbn)){
            libraryRepository.save(bookStock);
        }
        else{
            BookStock preexistingBookStock = libraryRepository.findByIsbn(isbn);
            changeQuantity(preexistingBookStock, preexistingBookStock.getTotalCopies()+ bookStock.getTotalCopies());
        }
        for(int i=0;i<n;i++){
            Book book = new Book(isbn);
            bookRepository.save(book);
        }
    }

    public List<BookStock> getInventory() {
        return libraryRepository.findAll();
    }

    // issuing the book by assigning book to customer and assigning it's issue date and return days
    // in book stocks,for the book stock increasing the number of issued copies by 1
    @Transactional
    public boolean issueBook(int isbn,int customerID) {
        BookStock bookStock = libraryRepository.findByIsbn(isbn);
        if(bookStock==null || bookStock.getAvailable()==0){
            return false;
        }
        List<Book> books = bookRepository.findBookByIsbn(isbn);
            for (Book book : books) {
                if (book.getIssuedDate() == null) {
                    book.setCustomerID(customerID);
                    book.setIssuedDate(LocalDate.now());
                    book.setReturnDays(7);
                    bookStock.setIssued(bookStock.getIssued()+1);
                    return true;
                }
        }
        return false;
    }

    // returning the book by removing the customer assignment from book and removing it's issue date and return days
    // in book stocks,for the book stock decreasing the number of issued copies by 1
    @Transactional
    public boolean returnBook(int bookId) {
        if(!bookRepository.findById(bookId).isPresent()){
            return false;
        }
        Book book = bookRepository.findBookByBookId(bookId);
        if(book.getCustomerID()==0){
            return false;
        }
        int isbn = book.getIsbn();
        book.setReturnDays(0);
        book.setCustomerID(0);
        book.setIssuedDate(null);
        BookStock bookStock = libraryRepository.findByIsbn(isbn);
        bookStock.setIssued(bookStock.getIssued()-1);
        return true;
    }

    // When a book is discontinued all of entries are deleted from book table and
    // In bookStock table, it's stock is deleted
    public boolean discontinueBook(int isbn) {
        BookStock bookStock = libraryRepository.findByIsbn(isbn);
        if(bookStock ==null){
            return false;
        }
        libraryRepository.delete(bookStock);
        List<Book> books = bookRepository.findBookByIsbn(isbn);
        bookRepository.deleteAll(books);
        return true;
    }
    @Transactional
    // in the book table, extending return time
    public boolean extendReturnTime(int bookId, int extension) {
        Book book = bookRepository.findBookByBookId(bookId);
        if(book==null){
            return false;
        }
        book.setReturnDays(book.getReturnDays()+extension);
        return true;
    }

    public List<BookStock> searchBookByTitle(String bookTitle) {
        return libraryRepository.findByBookTitle(bookTitle);
    }
    public List<BookStock> searchBookByAuthor(String bookAuthor) {
        return libraryRepository.findByBookAuthor(bookAuthor);
    }

    public List<Book> showAllBooks() {
        return bookRepository.findAll();
    }
}
