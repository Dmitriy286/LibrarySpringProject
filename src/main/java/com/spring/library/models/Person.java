package com.spring.library.models;

public class Person {
    private int id;
    private String fullName;
    private int yearOfBirth;

    public Person() {

    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getDateOfBirth() {
        return yearOfBirth;
    }

    public void setDateOfBirth(int dateOfBirth) {
        this.yearOfBirth = dateOfBirth;
    }
}
