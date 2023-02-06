package com.spring.library.dao;

import com.spring.library.models.Book;
import com.spring.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        List<Book> books = jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));

        return books;
    }

    public Book findById(int id) {
        Book book = jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new Object[]{id},
                        new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);

        return book;
    }

    public Optional<Book> findByNameAuthorAndYear(String bookName, String author, int year) {
        return jdbcTemplate.query("SELECT * FROM book WHERE name = ? AND author = ? AND year = ?",
                new Object[]{bookName, author, year},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());

    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=? WHERE book_id=?",
                book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    public void setToPerson(int bookId, int personId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", personId, bookId);
    }

    public void returnBook(int bookId) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", null, bookId);
    }
}
