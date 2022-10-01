package com.example.demo.Library.Entity;
import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity(name = "Book")
public class Book {
    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )
    private int bookId;
    private int isbn;
    private LocalDate issuedDate;
    private int returnDays;
    private int customerID;
    public Book() {
    }

    public Book(int isbn) {
        this.isbn = isbn;
    }

    public Book(int isbn, LocalDate issuedDate, int returnDays, int customerID) {
        this.isbn = isbn;
        this.issuedDate = issuedDate;
        this.returnDays = returnDays;
        this.customerID = customerID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public int getReturnDays() {
        return returnDays;
    }

    public void setReturnDays(int returnDays) {
        this.returnDays = returnDays;
    }
}
