package controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MemberCheckTest {

    @Test
    public void checkName_EmptryString() {
        assertEquals("Error: Name is missing",MemberCheck.checkName(""));
    }

    @Test
    public void checkName_Null() {
        assertEquals("Error: Name is missing",MemberCheck.checkName(null));
    }

    @Test
    public void checkName_SpecialCharacters() {
        assertEquals("Error: Name cannot contain anything but letters and spaces",MemberCheck.checkName("Dino Spre$o"));
    }

    @Test
    public void checkName_ValidName() {
        assertEquals("Dino Spreco",MemberCheck.checkName("Dino Spreco"));
    }

    @Test
    public void checkIdDocNumber_EmptyString() {
        assertEquals("Error: Identification Document Number is missing",MemberCheck.checkIdDocNumber(""));
    }

    @Test
    public void checkIdDocNumber_Null() {
        assertEquals("Error: Identification Document Number is missing",MemberCheck.checkIdDocNumber(null));
    }

    @Test
    public void checkIdDocNumber_ValidIdDocNumber() {
        assertEquals("02CVX",MemberCheck.checkIdDocNumber("02CVX"));
    }
}
