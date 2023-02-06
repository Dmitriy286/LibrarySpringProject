package com.spring.library.controllers;

import com.spring.library.dao.BookDAO;
import com.spring.library.dao.PersonDAO;
import com.spring.library.models.Book;
import com.spring.library.models.Person;
import javax.validation.Valid;

import com.spring.library.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String showAllBooks(Model model) {
        System.out.println("Init show all books");
        List<Book> books;
        books = bookDAO.findAll();
        for (Book b : books) {
            System.out.println(b);
        }
        model.addAttribute("books", books);
        return "books/show-all";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model, @ModelAttribute("emptyPerson") Person emptyPerson) {
        Book book;
        List<Person> people;
        book = bookDAO.findById(id);
        Person person = personDAO.findById(book.getPersonId());
        model.addAttribute("person", person);
        people = personDAO.findAll();
        model.addAttribute("book", book);
        model.addAttribute("people", people);
        return "books/show";
    }

    @GetMapping("/new")
    public String getNewBookForm(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        System.out.println("Controller:");
        System.out.println(book);
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String getEditBookForm(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.findById(id);
        model.addAttribute("book", book);
        return "books/edit";
    }

    @PostMapping("/{id}")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book,
                           BindingResult bindingResult) {
        System.out.println("Before validator");
        bookValidator.validate(book, bindingResult);
        System.out.println("After validator");

        if (bindingResult.hasErrors()) {
            System.out.println("Inside haserrors");
            System.out.println(bindingResult.getAllErrors());
            return "books/edit";
        }
        System.out.println("Before update");
        bookDAO.update(id, book);
        System.out.println("After update");

        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/{id}/setbook")
    public String setBookToPerson(@PathVariable("id") int id, @ModelAttribute("emptyPerson") Person emptyPerson) {
        bookDAO.setToPerson(id, emptyPerson.getPersonId());
        return "redirect:/books/{id}";
    }

    @PostMapping("/{id}/returnbook")
    public String returnBook(@PathVariable("id") int id) {
        bookDAO.returnBook(id);
        return "redirect:/books/{id}";
    }
}
