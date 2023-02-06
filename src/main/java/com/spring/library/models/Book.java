package com.spring.library.models;

import javax.validation.constraints.*;

public class Book {
    private int bookId;
    private Integer personId;

    @NotEmpty(message = "Введите название книги")
    @Size(min = 1, max = 50, message = "Название книги должно содержать от 1 до 50 символов")
    private String name;
    @NotEmpty(message = "Введите имя автора")
    @Size(min = 2, max = 50, message = "Имя автора должно содержать от 2 до 50 символов")
    private String author;
    @Min(value = 1452, message = "Рукописные издания допечатной эры не хранятся в нашей библиотеке")
    @Max(value = 2023, message = "Введите год до 2023")
    private int year;


    public Book() {

    }

    public Book(Integer personId, String name, String author, int year) {
        if (personId == null) {
            this.personId = 0;
        } else {
            this.personId = personId;
        }
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        if (personId == null) {
            this.personId = 0;
        } else {
            this.personId = personId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        int persId = this.personId == null ? 0 : getPersonId();
        return getBookId() + ", " + persId + ", " + getName();
    }
}
