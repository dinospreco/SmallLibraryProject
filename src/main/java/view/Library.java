package view;

import database.BookDAO;
import database.MemberDAO;
import model.Book;
import model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Library {
    public static void main(String[] args) {

        addThreeBooksAndMembers();
        printAllMembers();
        printAllBooks();

    }

    public static void printAllMembers() {
        Map members = new HashMap();
        MemberDAO memberDao = new MemberDAO();
        members = memberDao.getAll();

        for (Object key: members.keySet()) {
            System.out.println(members.get(key).toString());
        }
    }

    public static void printAllBooks() {
        Map books = new HashMap();
        BookDAO bookDao = new BookDAO();
        books = bookDao.getAll();

        for (Object key: books.keySet()) {
            System.out.println(books.get(key).toString());
        }

    }

    public static void addThreeBooksAndMembers() {
        BookDAO bookDao = new BookDAO();
        MemberDAO memberDAO = new MemberDAO();

        Book book1 = new Book("dinina prva knjiga","dino spreco","12345",2018);
        Book book2 = new Book("dinina druga knjiga","dino spreco","54321",2015);
        Book book3 = new Book("dinina treca knjiga","dino spreco","31412",2010);

        Member member1 = new Member("Dino Spreco" , "131AF5S");
        Member member2 = new Member("Spreco Dino" , "fa145S");
        Member member3 = new Member("Edin Spreco" , "13000S");

        System.out.println("Adding book 1");
        bookDao.add(book1);
        System.out.println("Book 1 added");

        System.out.println("Adding book 2");
        bookDao.add(book2);
        System.out.println("Book 2 added");

        System.out.println("Adding book 3");
        bookDao.add(book3);
        System.out.println("Book 3 added");

        System.out.println("Adding member 1");
        memberDAO.add(member1);
        System.out.println("Member 1 added");

        System.out.println("Adding member 2");
        memberDAO.add(member2);
        System.out.println("Member 2 added");

        System.out.println("Adding member 3");
        memberDAO.add(member3);
        System.out.println("Member 3 added");
    }

}
