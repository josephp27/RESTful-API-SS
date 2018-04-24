package com.solstice.api;

import java.util.regex.Pattern;

public class Validatior {
    //taken from: https://www.geeksforgeeks.org/check-email-address-valid-not-java/
    protected static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    protected static boolean isValidNumber(String number) {
        String numberRegex = "\\+?1?\\s*\\(?-*\\.*(\\d{3})\\)?\\.*-*\\s*(\\d{3})\\.*-*\\s*(\\d{4})$";

        Pattern pat = Pattern.compile(numberRegex);

        //personal numbers are not required
        if (number == null || number.equals(""))
            return true;
        return pat.matcher(number).matches();
    }

    protected static String formatNumber(String number){
        return number.replaceAll("[-()+]", "").trim();
    }

    protected static void validate(Contact c){
        if(!isValidEmail(c.getEmail())) {
            throw new IllegalArgumentException("The 'Email' must be a valid email");
        }
        if(!isValidNumber(c.getPersonalPhone())) {
            throw new IllegalArgumentException("The 'Personal Phone Number' must be a valid number");
        }
        if(!isValidNumber(c.getWorkPhone())) {
            throw new IllegalArgumentException("The 'Work Phone Number' must be a valid number");
        }
    }

}
