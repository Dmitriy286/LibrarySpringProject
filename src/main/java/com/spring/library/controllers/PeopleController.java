package com.spring.library.controllers;

import com.spring.library.dao.PersonDAO;
import com.spring.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String showAllPeople(Model model) {
        List<Person> people = personDAO.findAll();
        model.addAttribute("people", people);
        return "people/show-all";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        Person person = personDAO.findById(id);
        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String getNewPersonForm(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") Person person) {
        System.out.println(person);
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String getEditPersonForm(@PathVariable("id") int id, Model model) {
        Person person = personDAO.findById(id);
        model.addAttribute("person", person);
        return "people/edit";
    }

    @PostMapping("/{id}")
    public String editPerson(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        System.out.println(person);
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
