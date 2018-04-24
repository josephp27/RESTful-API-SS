package com.solstice.api;

public class Student {
    private Long id;
    private String name;
    private String email;
    private String number_work;
    private String number_personal;

    public Student() {
        super();
    }

    public Student(Long id, String name, String email, String number_work, String number_personal) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.number_work = formatNumber(number_work);
        this.number_personal = formatNumber(number_personal);
    }

    public Student(String name, String email) {
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
        this.number_work = formatNumber(number_work);
    }

    public void setPersonalPhone(String number_personal){
        this.number_personal = formatNumber(number_personal);
    }

    protected static String formatNumber(String number){
        return number.replaceAll("[-()+]", "").trim();
    }

    @Override
    public String toString() {
        return String.format("Student [id=%s, name=%s, email=%s, work=%s, personal=%s]", id, name, email,
                number_work, number_personal);
    }

}