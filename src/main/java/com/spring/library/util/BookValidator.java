package com.spring.library.util;

import com.spring.library.dao.BookDAO;
import com.spring.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;
        if (bookDAO.findByNameAuthorAndYear(book.getName(), book.getAuthor(), book.getYear()).isPresent()) {
            errors.rejectValue("name", "", "Такая книга уже зарегистрирована в библиотечной системе");
        }
    }
}
