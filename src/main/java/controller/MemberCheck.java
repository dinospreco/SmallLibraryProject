package controller;

import java.util.regex.Pattern;

public class MemberCheck {

    /*
     * Check Name
     *
     * Checking if name is valid
     * Same as authors name but with different messages
     */
    public static String checkName(String name) {
        String regx = "^[\\p{L} .'-]+$";

        if (name == null || name.trim().equals("")) {
            return "Error: Name is missing";
        }
        else if (!Pattern.matches(regx,name)) {
            return "Error: Name cannot contain anything but letters and spaces";
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
            return "Error: Identification Document Number is missing";
        }
        else {
            return idDocNumber;
        }
    }

}
