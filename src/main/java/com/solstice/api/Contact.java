package com.solstice.api;

public class Contact {
    private Long id;
    private String name;
    private String email;
    private String number_work;
    private String number_personal;
    private String company;
    private String address;
    private String profileImage;
    private String bd;

    public Contact() {
        super();
    }

    public Contact(Long id, String name, String email, String number_work, String number_personal, String company,
                   String address, String profileImage, String bd) {
        super();
        this.id = id;
        this.name = Validator.processSpaces(name);
        this.email = email;
        this.number_work = Validator.formatNumber(number_work);
        this.number_personal = Validator.formatNumber(number_personal);
        this.company = company;
        this.address = address;
        this.profileImage = profileImage;
        this.bd = bd;
        Validator.validate(this);
    }

    public Contact(String name, String email) {
        super();
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWorkPhone(){
        return number_work;
    }

    public String getPersonalPhone(){
        return number_personal;
    }

    public void setWorkPhone(String number_work){
        this.number_work = Validator.formatNumber(number_work);
    }

    public void setPersonalPhone(String number_personal){
        this.number_personal = Validator.formatNumber(number_personal);
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getCompany(){
        return company;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getAddress(){
        return address;
    }

    public void setProfileImage(String image){
        this.profileImage = image;
    }

    public String getProfileImage(){
        return profileImage;
    }

    public String getBd(){
        return  bd;
    }

    public void setBd(String bd){
        this.bd = bd;
    }

    @Override
    public String toString() {
        return String.format("Contact [id=%s, name=%s, email=%s, work=%s, personal=%s]", id, name, email,
                number_work, number_personal);
    }

}