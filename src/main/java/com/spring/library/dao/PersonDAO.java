package com.spring.library.dao;

import com.spring.library.models.Book;
import com.spring.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        List<Person> people = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));

        return people;
    }


    public Person findById(int id) {
        Person person = jdbcTemplate.query("SELECT * FROM person WHERE person_id=?;", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
        .stream().findAny().orElse(null);

        return person;
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (full_name, year_of_birth) VALUES (?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE person SET full_name=?, year_of_birth=? WHERE person_id=?",
                person.getFullName(), person.getYearOfBirth(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public List<Book> getPersonBooks(int id) {
        List<Book> books = jdbcTemplate.query("SELECT name, author, year FROM book WHERE person_id=?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));

        return books;
    }
}
