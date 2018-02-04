/*
 * Menu class is class that deals with menues.
 *
 * @author Dino Spreco
 * @date 2.2.2018.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    public static void printStatus() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. YYYY");
        Date date = new Date();
        System.out.println(" - Welcome, today is " + sdf.format(date));
    }

    public static int printMainMenu() {
        Scanner input = new Scanner(System.in);
        int currentOption;
        printStatus();
        System.out.println();
        System.out.println("MAIN MENU OPTIONS:");
        System.out.println("1. Rent book");
        System.out.println("2. Books");
        System.out.println("3. Members");
        System.out.println("4. Exit");
        System.out.print("Type in option: ");
        currentOption = input.nextInt();

        return currentOption;
    }

    public static int rentBook(ArrayList<LibraryBook> books , ArrayList<Member> members) {
        Scanner input = new Scanner(System.in);
        int memberId;
        int memberIndex = -1;
        int numberOfBooksRented = 0;
        boolean foundMember = false;

        int bookId;
        int bookIndex = -1;
        boolean foundBook = false;

        printStatus();
        System.out.println("RENT BOOK");
        System.out.print("Member id: ");
        memberId = input.nextInt();

        for (Member member : members) {
            if (member.getId() == memberId) {
                memberIndex = members.indexOf(member);
                foundMember = true;
                break;
            }
        }

        if (!foundMember) {
            System.out.println("Member with id: " + memberId + " was not found");
            return 0;
        }
        else if (members.get(memberIndex).isMembershipExpired()) {
            System.out.println("Member with id: " + memberId + " cannot rent any books. Membership expired");
        }
        else if (members.get(memberIndex).getNumberOfRentedBooks() >= 3) {
            System.out.println("Memeber wiht id: " + memberId + " has reached limit of 3 rented books");
        }

        System.out.print("Book id: ");
        bookId = input.nextInt();

        for (LibraryBook book : books) {
            if (book.getId() == bookId){
                bookIndex = books.indexOf(book);
                foundBook = true;
                break;
            }
        }

        if (!foundBook) {
            System.out.println("Book with id: " + bookId + " was not found");
            return 0;
        }
        else if (books.get(bookIndex).isRented()) {
            System.out.println("Book is already rented");
            return 0;
        }
        else {
            books.get(bookIndex).setRented(true);
            books.get(bookIndex).setMemberIdBookIsRentedTo(memberId);
            members.get(memberIndex).increaseNumberOfRentedBooks();
            System.out.println("Book (id: " + bookId + ") is rented to member (id: " + memberId + ")");
            return 0;
        }
    }

    //<editor-fold desc="BOOKS">
    //Prints basic BOOK MENU
    public static int printBooksMenu() {
        Scanner input = new Scanner(System.in);
        int currentOption;
        printStatus();
        System.out.println();
        System.out.println("BOOK MENU OPTIONS");
        System.out.println("1. Add book");
        System.out.println("2. Remove book");
        System.out.println("3. Print book info");
        System.out.println("4. List all rented books");
        System.out.println("5. List all books that are not returned on time");
        System.out.println("0. BACK");
        System.out.print("Type in option: ");

        currentOption = input.nextInt();

        return currentOption;
    }

    //This method is adding a book into ArrayList. Returns 2 because in menu options "2" is Book Menu so it will print Book Menu.
    public static int addBook(ArrayList<LibraryBook> books) {
        Scanner input = new Scanner(System.in);
        String title;
        String author;
        int publicationYear;
        String isbn;

        printStatus();
        System.out.println();
        System.out.println("ADDING BOOK TO LIBRARY");
        System.out.print("Title: ");
        title = input.nextLine();

        System.out.print("Author: ");
        author = input.nextLine();

        System.out.print("Publication year: ");
        publicationYear = input.nextInt();
        input.nextLine();

        System.out.print("ISBN: ");
        isbn = input.nextLine();

        try {
            LibraryBook book = new LibraryBook(title,author,publicationYear,isbn);
            books.add(book);
            System.out.println(book.toString());
            System.out.println("is now added");
            return 2;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return 2;
        }
    }

    //This method removes book from ArrayList. Returns 2 because in menu options "2" is Book Menu so it will print Book Menu.
    public static int removeBook(ArrayList<LibraryBook> books) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy");
        Calendar cal = Calendar.getInstance();
        Scanner input = new Scanner(System.in);
        int id;
        int index = -1;
        char yorn;

        printStatus();
        System.out.println("REMOVING BOOK FROM LIBRARY");
        System.out.println("Remove book by id");
        System.out.print("Type id: ");
        id = input.nextInt();

        for (LibraryBook book : books) {
            if (book.getId() == id) {
                index = books.indexOf(book);
                break;
            }
        }

        if (index == -1) {
            System.out.println("Book with id: " + id + " was not found");
            return 2;
        }
        else if (books.get(index).isRented()) {
            System.out.println("Book cant be removed");
            System.out.println("REASON: The book is rented to user with id:" + books.get(index).getMemberIdBookIsRentedTo());
            cal.setTime(books.get(index).getDateRented());
            cal.add(Calendar.DATE , 14);
            System.out.println("Expected to be returned on: " + sdf.format(cal.getTime()));
            return 2;
        }
        else {
            System.out.println("Removing book:");
            System.out.println("Id: " + books.get(index).getId());
            System.out.println("Title: " + books.get(index).getTitle());
            System.out.println("Author: " + books.get(index).getAuthor());
            System.out.print("ARE YOU SURE? (y/n):");
            yorn = input.next().charAt(0);
            if (yorn == 'y' || yorn == 'Y') {
                books.remove(index);
                System.out.println("BOOK WAS REMOVED");
                return 2;
            }
            else if (yorn == 'n' || yorn == 'N') {
                return 2;
            }
            else {
                System.out.println("WRONG ANSWER, book wont be removed");
                return 2;
            }
        }
    }

    //This method prints info of a book. Returns 2 because in menu options "2" is Book Menu so it will print Book Menu.
    public static int printBookInfoMenu(ArrayList<LibraryBook> books) {
        Scanner input = new Scanner(System.in);
        int id;
        int index = -1;

        System.out.println("PRINT BOOK INFO");
        System.out.print("Type in id: ");
        id = input.nextInt();

        for (LibraryBook book : books) {
            if (book.getId() == id) {
                index = books.indexOf(book);
                break;
            }
        }

        if (index == -1) {
            System.out.println("Book was not found!");
            return 2;
        }
        else {
            printBookInfo(books.get(index));
        }
        return 2;
    }

    //Prints basic book info
    public static void printBookInfo(LibraryBook book) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy.");

        System.out.println("ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("ISBN: " + book.getIsbn());

        if (book.isRented()) {
            System.out.println("Rented: Book is rented to:");
            System.out.println("\tMember ID: " + book.getMemberIdBookIsRentedTo());
            System.out.println("\tOn: " + sdf.format(book.getDateRented()));
        }
        else {
            System.out.println("Rented: Book is not rented");
        }
    }

    //Lists all books that are rented
    public static int listAllRentedBooks(ArrayList<LibraryBook> books) {
        boolean foundRentedBook = false;
        for (LibraryBook book : books) {
            if (book.isRented()) {
                foundRentedBook = true;
                printBookInfo(book);
            }
        }
        if (!foundRentedBook) {
            System.out.println("None of the books are rented");
        }
        return 2;
    }

    //Listt all books that are not returned on time
    public static int listAllBooksThatAreNotReturnedOnTime(ArrayList<LibraryBook> books) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = new Date();
        boolean foundRentedBook = false;

        for (LibraryBook book : books) {
            if (book.isRented()) {
                cal.setTime(book.getDateRented());
                cal.add(Calendar.DATE,14);
                if (cal.before(currentDate)) {
                    foundRentedBook = true;
                    printBookInfo(book);
                }
            }
        }
        if (!foundRentedBook) {
            System.out.println("None of the books are rented");
        }
        return 2;
    }
    //</editor-fold>

    //<editor-fold desc="MEMBERS">
    // Prints MEMBER MENU
    public static int printMembersMenu() {
        Scanner input = new Scanner(System.in);
        int currentOption;
        printStatus();
        System.out.println();
        System.out.println("MEMBER MENU OPTIONS");
        System.out.println("1. Add member");
        System.out.println("2. Remove member");
        System.out.println("3. List rented books by member");
        System.out.println("4. Print member info");
        System.out.println("5. Renew membership");
        System.out.println("6. List all members");
        System.out.println("0. BACK");
        currentOption = input.nextInt();
        return currentOption;
    }

    //Adds new member. Returns 3 because of menu cases in Library class
    public static int addMember(ArrayList<Member> members) {
        Scanner input = new Scanner(System.in);
        String name;
        String idDocNumber;

        printStatus();
        System.out.println();
        System.out.println("ADDING NEW MEMBER");
        System.out.print("Name: ");
        name = input.nextLine();

        System.out.print("Identification document number: ");
        idDocNumber = input.nextLine();

        try {
            Member member = new Member(name,idDocNumber);
            members.add(member);
            System.out.println(member.toString());
            System.out.println("is now added");
            return 3;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return 3;
        }
    }

    //Removes existing member. Returns 3 because of menu cases in Library class
    public static int removeMember(ArrayList<Member> members) {
        Scanner input = new Scanner(System.in);
        int id;
        int index = -1;
        boolean foundMember = false;

        char yorn;

        printStatus();
        System.out.println();
        System.out.println("REMOVING MEMBER");
        System.out.print("Type in id: ");
        id = input.nextInt();

        for (Member member : members) {
            if (member.getId() == id) {
                index = members.indexOf(member);
                foundMember = true;
                break;
            }
        }

        if (!foundMember) {
            System.out.println("Member wiht id: " + id + " was not found");
            return 3;
        }
        else if(members.get(index).getNumberOfRentedBooks() != 0) {
            System.out.println("Cannot remove this member. REASON: Member didnt return all rented books");
            return 3;
        }
        else {
            System.out.println("Removing member: ");
            System.out.println("Id: " + members.get(index).getId());
            System.out.println("Name: " + members.get(index).getName());
            System.out.print("ARE YOU SURE? (y/n)");
            yorn = input.nextLine().charAt(0);

            if (yorn == 'y' || yorn == 'Y') {
                System.out.println("MEMBER IS REMOVED");
                return 3;
            }
            else if (yorn == 'n' || yorn == 'N') {
                System.out.println("MEMBER IS NOT REMOVED");
                return 3;
            }
            else {
                System.out.println("Wrong answer. Member is not removed");
                return 3;
            }
        }
    }

    //Prints all books that are rented by. Returns 3 because of menu cases in Library class
    public static int listRentedBooksByMember(ArrayList<LibraryBook> books, ArrayList<Member> members) {
        Scanner input = new Scanner(System.in);
        int id;
        int index = -1;
        boolean foundMember = false;

        printStatus();
        System.out.println();
        System.out.println("List all books that are rented by member");
        System.out.print("Member Id: ");
        id = input.nextInt();

        for (Member member : members) {
            if (member.getId() == id) {
                index = members.indexOf(member);
                foundMember = true;
                break;
            }
        }
        if (!foundMember) {
            System.out.println("Member with id: " + id + " was not found");
            return 3;
        }
        else if (members.get(index).getNumberOfRentedBooks() == 0) {
            System.out.println("Member has no rented books");
            return 3;
        }
        else {
            for (LibraryBook book : books) {
                if (book.getMemberIdBookIsRentedTo() == id) {
                    printBookInfo(book);
                }
            }
            return 3;
        }
    }

    //Prints member info
    public static void printMemberInfo(Member member) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy.");
        Date currentDate = new Date();

        System.out.println("MEMBER INFO:");
        System.out.println("Id: " + member.getId());
        System.out.println("Name: " + member.getName());
        System.out.println("Number of rented books" + member.getNumberOfRentedBooks());
        if (currentDate.after(member.getMembershipExpirationDate())) {
            System.out.println("Membership: EXPIRED on " + sdf.format(member.getMembershipExpirationDate()));
        }
        else {
            System.out.println("Membership: VALID until " + sdf.format(member.getMembershipExpirationDate()));
        }
    }

    //Prints member info menu. Returns 3 because of menu cases in Library class
    public static int printMemberInfoMenu(ArrayList<Member> members) {
        Scanner input = new Scanner(System.in);
        int id;
        boolean foundMember = false;

        printStatus();
        System.out.println();
        System.out.println("PRINT MEMBER INFO");
        System.out.print("Type memeber id: ");
        id = input.nextInt();

        for (Member member : members) {
            if (member.getId() == id) {
                printMemberInfo(member);
                return 3;
            }
        }
        if (!foundMember) {
            System.out.println("Member with id: " + id + " was not found");
            return 3;
        }
        return 3;
    }

    //Renews membership. Returns 3 because of menu cases in Library class
    public static int renewMembership(ArrayList<Member> members) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MM. yyyy.");
        Scanner input = new Scanner(System.in);
        int id;

        printStatus();
        System.out.println();
        System.out.println("RENEWING MEMBERSHIP");
        System.out.print("Type members id: ");
        id = input.nextInt();

        for (Member member : members) {
            if (member.getId() == id) {
                System.out.println("Membership for:");
                printMemberInfo(member);
                member.extendMembership();
                System.out.println("is now extended by 1 year");
                System.out.println("New expiration date: " + sdf.format(member.getMembershipExpirationDate()));
                return 3;
            }
        }
        return 3;

    }

    //Lists all members.Returns 3 because of menu cases in Library class
    public static int listAllmembers (ArrayList<Member> members) {
        for (Member member : members) {
            printMemberInfo(member);
        }
        return 3;
    }
    //</editor-fold>
}
