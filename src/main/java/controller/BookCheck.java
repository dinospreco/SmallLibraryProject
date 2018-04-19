package controller;

import java.util.Calendar;
import java.util.regex.Pattern;

public class BookCheck {

    /*
     * Check Title
     *
     * Checking if title is valid
     * Title is valid if is not "null" or empty string
     */
    public static String checkTitle(String title) throws IllegalArgumentException {
        if (title == null || title.equals("")) {
            return "Error: Title is missing";
        } else {
            return title;
        }
    }

    /*
     * Check Author
     *
     * Checking if Authors Name is valid
     * Authors Name is valid if conatins only letters and spaces.
     * Authors Name cannot be "null" or empty string
     */
    public static String checkAuthor(String author) {
        String regx = "^[\\p{L} .'-]+$";

        if (author == null || author.trim().equals("")) {
            return "Error: Authors name is missing";
        }
        else if (!Pattern.matches(regx,author)) {
            return "Error: Author name cannot contain anything but letters and spaces";
        }
        else {
            return author;
        }
    }

    /*
     * Check Publication Year
     *
     * Checking if Publication Year is valid
     * Publication year cannot be less than zero, or in the future.
     */
    public static String checkPublicationYear(int publicationYear) {
        if (publicationYear < 0) {
            return "Error: Publication year cannot be negative number";
        }
        else if (publicationYear > Calendar.getInstance().get(Calendar.YEAR)) {
            return "Error: Publication year cannot be in the future";
        }
        else {
            return Integer.toString(publicationYear);
        }
    }

    /*
     * Check Isbn
     *
     * Checking if ISBN is valid
     * Isbn is valid if contains only digits, and its 13 digits long
     */
    public static String checkIsbn(String isbn) {
        String regx = "[0-9]+";

        if (isbn == null || isbn.trim().equals("")) {
            return "Error: ISBN is missing";
        }
        if (!Pattern.matches(regx,isbn)) {
            return "Error: ISBN cannot contain anything but digits";
        }
        else if (isbn.length() != 13) {
            return "Error: ISBN must be 13 digits long";
        }
        else {
            return isbn;
        }
    }

    /*
     * THIS METHOD IS NOT USED
     *
     * Check id
     *
     * Checking if id is valid
     * Id is valid if its 0 < id
     *
    public static int checkId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        else {
            return id;
        }
    }
    /**/


}
