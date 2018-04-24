package com.solstice.api;

public class Contact {
    private Long id;
    private String name;
    private String email;
    private String number_work;
    private String number_personal;
    private String company;

    public Contact() {
        super();
    }

    public Contact(Long id, String name, String email, String number_work, String number_personal, String company) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.number_work = Validatior.formatNumber(number_work);
        this.number_personal = Validatior.formatNumber(number_personal);
        this.company = company;
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
        this.number_work = Validatior.formatNumber(number_work);
    }

    public void setPersonalPhone(String number_personal){
        this.number_personal = Validatior.formatNumber(number_personal);
    }

    public void setCompany(String company){
        this.company = company;
    }

    public String getCompany(){
        return company;
    }

    @Override
    public String toString() {
        return String.format("Contact [id=%s, name=%s, email=%s, work=%s, personal=%s]", id, name, email,
                number_work, number_personal);
    }

}