package com.solstice.api;

import java.util.regex.Pattern;

public class Validator {
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


        if (number == null) {
            return false;
        }

        //personal numbers are not required
        if (number.equals("")) {
            return true;
        }

        return pat.matcher(number).matches();
    }

    protected static String formatNumber(String number){
        return number.replaceAll("[-()+]", "").trim();
    }

    protected static boolean isValidName(String name){
        return name.length() <= 255;
    }

    protected static String processSpaces(String name){
        //handles _ and + and %20 for spaces. did not include - because some have 2 last names split by a hyphen
        return name.replaceAll("[_+]", " ");
    }

    protected static void validate(Contact c){
        validateCompany(c.getCompany());
        validateEmail(c.getEmail());
        validateWork(c.getWorkPhone());
        validatePersonal(c.getPersonalPhone());
        validateName(c);
        validateAddress(c);
        validateProfileImage(c.getProfileImage());
        validateBD(c);
    }

    protected static void validateBD(Contact c){
        c.setBd(processSpaces(c.getBd()));
        if(c.getBd().length() > 255){
            throw new IllegalArgumentException("Birthday must be less than 255 characters!");
        }
    }

    protected static void validateProfileImage(String image){
        if(image.length() > 255){
            throw new IllegalArgumentException("Image must be a link less than 255 characters!");
        }
    }

    protected static void validateAddress(Contact c){
        c.setStreet(processSpaces(c.getStreet()).toUpperCase());
        c.setState(processSpaces(c.getState()).toUpperCase());
        c.setCity(processSpaces(c.getCity()).toUpperCase());

        if(c.getState().length() > 2) {
            throw new IllegalArgumentException("The 'State' must be less than 2 characters");
        }

        if(c.getStreet().length() > 255) {
            throw new IllegalArgumentException("The 'Street' must be less than 255 characters");
        }

        if(c.getCity().length() > 255) {
            throw new IllegalArgumentException("The 'City' must be less than 255 characters");
        }
    }

    protected static void validateCompany(String company){
        if(company.length() > 255) {
            throw new IllegalArgumentException("The 'Company' must be less than 255 characters");
        }
    }

    protected static void validatePersonal(String number){
        if(!isValidNumber(number)) {
            throw new IllegalArgumentException("The 'Personal Phone Number' must be a valid number");
        }
    }

    protected static void validateName(Contact c){
        c.setName(processSpaces(c.getName()));
        if(!isValidName(c.getName())) {
            throw new IllegalArgumentException("The 'name' must be a valid name");
        }
    }

    protected static void validateEmail(String email){
        if(!isValidEmail(email)) {
            throw new IllegalArgumentException("The 'Email' must be a valid email");
        }
    }

    protected static void validateWork(String work){
        if(!isValidNumber(work)) {
            throw new IllegalArgumentException("The 'Work Phone Number' must be a valid number");
        }
    }

}
