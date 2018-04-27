package controller;

import database.BookDAO;
import database.MemberDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Book;
import model.Member;

import java.sql.Date;
import java.util.*;

public class BookCtrl  {

    private static BookDAO dao = new BookDAO();
    private static Scanner input = new Scanner(System.in);

    public static void addBook(Book book) {
        dao.add(book);
    }

    public static void rentBook(Book book, Member member) {
        book.setRented(true);
        book.setDateRented(new Date(Calendar.getInstance().getTime().getTime()));
        book.setMemebersIdBookIsRentedTo(member.getId());
        dao.update(book);
    }

    public static Book getBookByIsbn(String isbn) {
        return dao.get("isbn = \'" + isbn + "\'");
    }

    public static Book getBookById(String id) {
        return dao.get("id = " + id);
    }

    public static Member getMemberWhoRentsThisBook(Book book) {
        MemberDAO memberDAO = new MemberDAO();
        return memberDAO.get("id = " + book.getMemebersIdBookIsRentedTo());
    }

    public static void returnBook(Book book) {
        book.setRented(false);
        book.setMemebersIdBookIsRentedTo(0);
        book.setDateRented(null);
        dao.update(book);
    }

    public static ObservableList<Book> getAllBooksForGui() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        List<Book> bookList = new ArrayList<Book>(dao.getAll().values());

        for (Book book : bookList) {
            books.add(book);
        }

        return books;
    }

}
