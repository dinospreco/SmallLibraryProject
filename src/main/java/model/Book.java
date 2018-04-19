package model;

import java.sql.Date;
import java.util.Objects;

public class Book {

    private int id;

    private String title;
    private String author;
    private String isbn;
    private int publicationYear;

    private boolean isRented;
    private Date dateRented;
    private int memebersIdBookIsRentedTo;

    public Book() {
    }

    public Book(int id) {
        this.id = id;
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public Book(int id, String title, String author, String isbn, int publicationYear, boolean isRented, Date dateRented, int memebersIdBookIsRentedTo) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.isRented = isRented;
        this.dateRented = dateRented;
        this.memebersIdBookIsRentedTo = memebersIdBookIsRentedTo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public int getMemebersIdBookIsRentedTo() {
        return memebersIdBookIsRentedTo;
    }

    public void setMemebersIdBookIsRentedTo(int memebersIdBookIsRentedTo) {
        this.memebersIdBookIsRentedTo = memebersIdBookIsRentedTo;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", isRented=" + isRented +
                ", dateRented=" + dateRented +
                ", memebersIdBookIsRentedTo=" + memebersIdBookIsRentedTo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getId() == book.getId() &&
                Objects.equals(getIsbn(), book.getIsbn());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getIsbn());
    }
}
