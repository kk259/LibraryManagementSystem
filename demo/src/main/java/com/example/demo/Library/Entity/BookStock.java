package com.example.demo.Library.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table
@Entity(name = "Library")
public class BookStock {
    @Id
    private int isbn;
    private String BookTitle;
    private String BookAuthor;
    private int totalCopies;
    private int issued;

    @Transient
    private int available;

    public BookStock() {
    }

    public BookStock(int isbn, String bookTitle, String bookAuthor, int totalCopies, int issued) {
        this.isbn = isbn;
        BookTitle = bookTitle;
        BookAuthor = bookAuthor;
        this.totalCopies = totalCopies;
        this.issued = issued;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        BookAuthor = bookAuthor;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies - issued;
    }

    public int getIssued() {
        return issued;
    }

    public void setIssued(int issued) {
        this.issued = issued;
    }

    public int getAvailable() {
        return totalCopies - issued ;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "BooksInformation{" +
                "isbn=" + isbn +
                ", BookTitle='" + BookTitle + '\'' +
                ", BookAuthor='" + BookAuthor + '\'' +
                ", totalCopies=" + totalCopies +
                ", issued=" + issued +
                ", available=" + available +
                '}';
    }
}
