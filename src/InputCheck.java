/*
 * InputCheck is class that contains static methods that check if input is correct
 *
 * @author Dino Spreco
 * @date 1.2.2018.
 */

import java.util.Calendar;
import java.util.regex.Pattern;

public class InputCheck {

    /*
     * Check Title
     *
     * Checking if title is valid
     * Title is valid if is not "null" or empty string
     */
    public static String checkTitle(String title) {
        if (title == null || title.equals("")) {
            throw new IllegalArgumentException("Title is missing");
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
        Pattern pattern = Pattern.compile(regx);

        if (author == null || author.trim().equals("")) {
            throw new IllegalArgumentException("Authors name is missing");
        }
        else if (!Pattern.matches(regx,author)) {
            throw new IllegalArgumentException("Author name cannot contain anything but letters and spaces");
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
    public static int checkPublicationYear(int publicationYear) {
        if (publicationYear < 0) {
            throw new IllegalArgumentException("Publication year cannot be negative number");
        }
        else if (publicationYear > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new IllegalArgumentException("Publication year cannot be in the future");
        }
        else {
            return publicationYear;
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
        Pattern pattern = Pattern.compile(regx);
        if (isbn == null || isbn.trim().equals("")) {
            throw new IllegalArgumentException("ISBN is missing");
        }
        if (!Pattern.matches(regx,isbn)) {
            throw new IllegalArgumentException("ISBN cannot contain anything but digits");
        }
        else if (isbn.length() != 13) {
            throw new IllegalArgumentException("ISBN must be 13 digits long");
        }
        else {
            return isbn;
        }
    }

    /*
     * Check id
     *
     * Checking if id is valid
     * Id is valid if its 0 < id
     */
    public static int checkId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        else {
            return id;
        }
    }

    /*
     * Check Name
     *
     * Checking if name is valid
     * Same as authors name but with different messages
     */
    public static String checkName(String name) {
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx);

        if (name == null || name.trim().equals("")) {
            throw new IllegalArgumentException("Name is missing");
        }
        else if (!Pattern.matches(regx,name)) {
            throw new IllegalArgumentException("Name cannot contain anything but letters and spaces");
        }
        else {
            return name;
        }
    }

    /*
     * Check Identification Document Number
     *
     * Checking if Identification Document Number is valid
     * Id Document Number is valid if its not empty string.
     * Depending on which ID Document member provided.
     */
    public static String checkIdDocNumber(String idDocNumber) {
        if (idDocNumber == null || idDocNumber.trim().equals("")) {
            throw new IllegalArgumentException("Identification Document Number is missing");
        }
        else {
            return idDocNumber;
        }
    }
}
