package controller;

import database.BookDAO;
import database.DAO;
import model.Book;

import java.util.Scanner;

public class BookCtrl  {

    private static DAO dao = new BookDAO();
    private static Scanner input = new Scanner(System.in);

    public static void addBook(Book book) {
        String inputCheck;
        String title;
        String author;
        String isbn;
        int publicationYear;

        System.out.println("Title: ");
        title = BookCheck.checkTitle(input.nextLine());



    }

}
