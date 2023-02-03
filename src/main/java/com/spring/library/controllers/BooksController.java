package com.spring.library.controllers;

import com.spring.library.dao.BookDAO;
import com.spring.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;

    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
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
    public String showBook(@PathVariable("id") int id, Model model) {
        Book book;
        book = bookDAO.findById(id);
        model.addAttribute("book", book);
        return "books/show";
    }

    @GetMapping("/new")
    public String getNewBookForm(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") Book book) {
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
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
