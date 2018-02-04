/*
 * Book Class for small library project.
 *
 * @author: Dino Spreco
 * @date: 29.1.2018.
 */

import java.util.Calendar;
import java.util.regex.Pattern;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;

    //<editor-fold desc="Construcotrs">
    public Book() {

    }

    public Book(String title, String author) {
        this.title = InputCheck.checkTitle(title);
        this.author = InputCheck.checkAuthor(author);

    }
    public Book(String title, String author, int publicationYear, String isbn) {
        this.title = InputCheck.checkTitle(title);
        this.author = InputCheck.checkAuthor(author);
        this.publicationYear = InputCheck.checkPublicationYear(publicationYear);
        this.isbn = InputCheck.checkIsbn(isbn);
    }

    //</editor-fold>

    //<editor-fold desc="Getters and Setters">

    // - title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = InputCheck.checkTitle(title);
    }

    // - author
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = InputCheck.checkAuthor(author);
    }

    // - publicationYear
    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = InputCheck.checkPublicationYear(publicationYear);
    }

    // - isbn
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = InputCheck.checkIsbn(isbn);
    }

    //</editor-fold>

    @Override
    public String toString() {
        /*  If publication year and isbn are not provided, they are assigned with default values.
            Changing default values to "unknown" looks better than 0 for a year or 0000000000000 for an isbn */

        String _publicationYear;
        if (publicationYear == 0) {
            _publicationYear = "unknown";
        }
        else {
            _publicationYear = Integer.toString(publicationYear);
        }
        String _isbn;
        if (isbn.equals("0000000000000")) {
            _isbn = "unknown";
        }
        else {
            _isbn = isbn;
        }
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + _publicationYear +
                ", isbn='" + _isbn + '\'' +
                '}';
    }
}
