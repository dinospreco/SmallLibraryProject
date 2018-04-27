package controller;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class BookCheckTest {

    @Test
    public void checkTitle_EmptyString() {
        assertEquals("Error: Title is missing",BookCheck.checkTitle(""));
    }

    @Test
    public void checkTitle_Null() {
        assertEquals("Error: Title is missing", BookCheck.checkTitle(null));
    }

    @Test
    public void checkTitle_Validtitle() {
        assertEquals("Title 1", BookCheck.checkTitle("Title 1"));
    }

    @Test
    public void checkAuthor_EmptyString() {
        assertEquals("Error: Authors name is missing", BookCheck.checkAuthor(""));
    }

    @Test
    public void checkAuthor_Null() {
        assertEquals("Error: Authors name is missing", BookCheck.checkAuthor(null));
    }

    @Test
    public void checkAuthor_SpecialCharacters() {
        assertEquals("Error: Author name cannot contain anything but letters and spaces", BookCheck.checkAuthor("Dino 123"));
    }

    @Test
    public void checkAuthor_OnlySpaces() {
        assertEquals("Error: Authors name is missing", BookCheck.checkAuthor("   "));
    }

    @Test
    public void checkPublicationYear_NegativeNumber() {
        assertEquals("Error: Publication year cannot be negative number", BookCheck.checkPublicationYear("-2"));
    }

    @Test
    public void checkPublicationYear_Zero() {
        assertEquals("0", BookCheck.checkPublicationYear("0"));
    }

    @Test
    public void checkPublication_Future() {
        assertEquals("Error: Publication year cannot be in the future", BookCheck.checkPublicationYear("2019"));
    }

    @Test
    public void checkPublication_CorrectYear() {
        assertEquals("1991", BookCheck.checkPublicationYear("1991"));
    }
    @Test
    public void checkPublication_ContainsLetters() {
        assertEquals("Error: Publication year must be number", BookCheck.checkPublicationYear("1991f"));
    }

    @Test
    public void checkIsbn_EmptyString() {
        assertEquals("Error: ISBN is missing", BookCheck.checkIsbn(""));
    }

    @Test
    public void checkIsbn_Null() {
        assertEquals("Error: ISBN is missing", BookCheck.checkIsbn(""));
    }

    @Test
    public void checkIsbn_LenghtShort() {
        assertEquals("Error: ISBN must be 13 digits long", BookCheck.checkIsbn("123"));
    }

    @Test
    public void checkIsbn_LenghtLong() {
        assertEquals("Error: ISBN must be 13 digits long", BookCheck.checkIsbn("1234566789101112"));
    }

    @Test
    public void checkIsbn_Letters() {
        assertEquals("Error: ISBN cannot contain anything but digits", BookCheck.checkIsbn("a234567891234"));
    }

    @Test
    public void checkIsbn_Spaces() {
        assertEquals("Error: ISBN cannot contain anything but digits", BookCheck.checkIsbn("123 567891234"));
    }

    @Test
    public void checkIsbn_CorrectIsbn() {
        assertEquals("1234567891234", BookCheck.checkIsbn("1234567891234"));
    }





}
