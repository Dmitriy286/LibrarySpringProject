package com.spring.library.dao;

import com.spring.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        System.out.println("findall");
        List<Person> people = new ArrayList<>();
        people = jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
//        for (Person p : people) {
//            System.out.println(p);
//        }
        return people;
    }


    public Person findById(int id) {
        Person person;
        person = jdbcTemplate.query("SELECT * FROM person WHERE person_id=?;", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
        .stream().findAny().orElse(null);
        System.out.println(person);
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

}
