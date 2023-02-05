package com.spring.library.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class Person {
    private int personId;
    @Pattern(regexp = "(?U)[А-Я]\\w+ (?U)[А-Я]\\w+ (?U)[А-Я]\\w+", message = "Должно соответствовать шаблону: Фамилия Имя Отчество")
    private String fullName;
    @Min(value = 1900)
    @Max(value = 2013, message = "В библиотеке можно регистрировать лиц только старше 10 лет")
    private int yearOfBirth;

    public Person() {

    }

    public Person(int personId, String fullName, int yearOfBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
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
