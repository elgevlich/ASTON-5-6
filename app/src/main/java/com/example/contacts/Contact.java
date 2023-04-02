package com.example.contacts;


public class Contact {
    private String name;
    private String lastname;
    private String number;

    public Contact(String name, String lastname, String number) {

        this.name = name;
        this.lastname = lastname;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastname;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
