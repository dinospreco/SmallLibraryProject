/*
 * LibraryBook class is extended Book class for using in Library application.
 *
 * @author Dino Spreco
 * @date 30.1.2018.
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class LibraryBook extends Book {
    private int id;
    private boolean rented;
    private Date dateRented;
    private int memberIdBookIsRentedTo;
    private static int totalNumberOfBooks;  //This is just for generating unique id

    //<editor-fold desc="Constructors">
    public LibraryBook() {

    }

    public LibraryBook(Book book) {
        super(book.getTitle(), book.getAuthor(), book.getPublicationYear(), book.getIsbn());

        //Setting default value: id = totalNumberOfBooks + 1, rented = false; dateRented = null, usersIdBookIsRentedTo = 0, totalNumberOFBooks++
        this.id = totalNumberOfBooks + 1;
        this.rented = false;
        this.dateRented = null;
        this.memberIdBookIsRentedTo = 0;
        LibraryBook.totalNumberOfBooks++;
    }

    public LibraryBook(String title, String author) {
        super(title, author);

        //Setting default value: id = totalNumberOfBooks + 1, rented = false; dateRented = null, usersIdBookIsRentedTo = 0, totalNumberOFBooks++
        this.id = totalNumberOfBooks + 1;
        this.rented = false;
        this.dateRented = null;
        this.memberIdBookIsRentedTo = 0;
        LibraryBook.totalNumberOfBooks++;
    }

    public LibraryBook(String title, String author, int publicationYear, String isbn) {
        super(title,author,publicationYear,isbn);

        //Setting default value: id = totalNumberOfBooks + 1, rented = false; dateRented = null, usersIdBookIsRentedTo = 0, totalNumberOFBooks++
        this.id = totalNumberOfBooks + 1;
        this.rented = false;
        this.dateRented = null;
        this.memberIdBookIsRentedTo = 0;
        LibraryBook.totalNumberOfBooks++;
    }
    //</editor-fold>

    //<editor-fold desc="Getters and Setters">
    // - id, only getters, id is set by constructor
    public int getId() {
        return id;
    }

    // - rented
    public boolean isRented() {
        return rented;
    }
    public void setRented(boolean rented) {
        if (rented) {
            this.dateRented = new Date();
        }
            this.rented = rented;
    }

    // - dateRented, dateRented is set by setRented method,
    public Date getDateRented() {
        return dateRented;
    }

    // - memberIdBookIsRentedTo
    public int getMemberIdBookIsRentedTo() {
        return memberIdBookIsRentedTo;
    }
    public void setMemberIdBookIsRentedTo(int memberIdBookIsRentedTo) {
        this.memberIdBookIsRentedTo = InputCheck.checkId(memberIdBookIsRentedTo);
    }

    // - totalNumberOfBooks
    public static int getTotalNumberOfBooks() {
        return totalNumberOfBooks;
    }
    //</editor-fold>

    @Override
    public String toString() {
        //  If publication year and isbn are not provided, they are assigned with default values.
        //  Changing default values to "unknown" looks better than 0 for a year or 0000000000000 for an isbn

        String _publicationYear;
        if (super.getPublicationYear() == 0) {
            _publicationYear = "unknown";
        } else {
            _publicationYear = Integer.toString(super.getPublicationYear());
        }
        String _isbn;
        if (super.getIsbn().equals("0000000000000")) {
            _isbn = "unknown";
        } else {
            _isbn = super.getIsbn();
        }

        //  If book is rented, dateRented will be the date that book is rented on.
        //  If book is not rented, than toString() method will write the last date the book was rented, or that was not rented ever.

        String _dateRented = "";
        String _usersIdBookIsRentedTo = "";
        if (dateRented == null) {
            _dateRented = "Book was never rented";
            _usersIdBookIsRentedTo = "Book was never rented";
        } else if (this.rented) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy.");
            _dateRented = sdf.format(dateRented);
            _usersIdBookIsRentedTo = Integer.toString(this.memberIdBookIsRentedTo);
        } else if (!this.rented) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy.");
            _dateRented = "last rented on " +sdf.format(dateRented);
        }
        return "LibraryBook{" +
                "id=" + id +
                ", title=" + "\'" + super.getTitle() + "\'" +
                ", author=" + "\'" + super.getAuthor() + "\'" +
                ", publicationYear=" + _publicationYear +
                ", isbn=" + _isbn +
                ", rented=" + rented +
                ", dateRented=" + _dateRented +
                ", usersIdBookIsRentedTo=" + _usersIdBookIsRentedTo +
                '}';
    }
}
