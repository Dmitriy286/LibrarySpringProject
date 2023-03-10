package com.spring.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

public class Person {
    private int personId;
    @Pattern(regexp = "(?U)[А-Я]\\w+ (?U)[А-Я]\\w+ (?U)[А-Я]\\w+", message = "Должно соответствовать шаблону: Фамилия Имя Отчество")
    private String fullName;
    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    private int yearOfBirth;

    public Person() {

    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return getPersonId() + ", " + getFullName() + ", " + getYearOfBirth();
    }
}
