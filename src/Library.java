/*
 * This class is main Library class that is handling member base and book archive,
 * also adding and removing books and members and all other operations that library might need
 *
 * @author Dino Spreco
 * @date 30.1.2018
 */

import java.util.*;

public class Library {
    public static void main(String[] args) {

        ArrayList<LibraryBook> books = new ArrayList<LibraryBook>();
        ArrayList<Member> members = new ArrayList<Member>();
        int option = 0;

        addDummieBooks(books);
        addDummieMembers(members);

        while (true) {

            switch (option) {
                case 0:
                    option = Menu.printMainMenu();
                    break;
                case 1:
                    option = Menu.rentBook(books,members);
                    break;
                case 2:
                    option = Menu.printBooksMenu()*10;
                    break;
                case 3:
                    option = Menu.printMembersMenu()*100;
                    break;
                case 4:
                    return;
                case 10:
                    option = Menu.addBook(books);
                    break;
                case 20:
                    option = Menu.removeBook(books);
                    break;
                case 30:
                    option = Menu.printBookInfoMenu(books);
                    break;
                case 40:
                    option = Menu.listAllRentedBooks(books);
                    break;
                case 50:
                    option = Menu.listAllBooksThatAreNotReturnedOnTime(books);
                    break;
                case 100:
                    option = Menu.addMember(members);
                    break;
                case 200:
                    option = Menu.removeMember(members);
                    break;
                case 300:
                    option = Menu.listRentedBooksByMember(books,members);
                    break;
                case 400:
                    option = Menu.printMemberInfoMenu(members);
                    break;
                case 500:
                    option = Menu.renewMembership(members);
                    break;
                case 600:
                    option = Menu.listAllmembers(members);
                    break;
            }
        }

    }

    public static void addDummieBooks(ArrayList<LibraryBook> books) {
        books.add(new LibraryBook("Knjiga 1","Prvi Autor" , 2015 , "0123456789321"));
        books.add(new LibraryBook("Knjiga 2","Drugi Autor Nije Prvi" , 2015 , "0123456789321"));
        books.add(new LibraryBook("Knjiga 3","Treci Autor Neki" , 2015 , "0123456789321"));
    }

    public static void addDummieMembers(ArrayList<Member> members) {
        members.add(new Member("Dino Spreco" , "15Af56"));
        members.add(new Member("Amer Sarajlic", "1faqXXX"));
    }
}