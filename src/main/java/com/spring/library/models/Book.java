package com.spring.library.models;

public class Book {
    private int bookId;
    private Integer personId;
    private String name;
    private String author;
    private int year;


    public Book() {

    }

    public Book(int bookId, Integer personId, String name, String author, int year) {
        this.bookId = bookId;
        if (personId == null) {
            this.personId = 0;
        } else {
            this.personId = personId;
        }
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(Integer personId, String name, String author, int year) {
        this.personId = personId;
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
