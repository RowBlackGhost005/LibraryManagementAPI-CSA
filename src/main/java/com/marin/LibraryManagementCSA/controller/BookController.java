package com.marin.LibraryManagementCSA.controller;

import com.marin.LibraryManagementCSA.entity.Book;
import com.marin.LibraryManagementCSA.service.BookService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.hibernate.PropertyValueException;

import java.sql.DataTruncation;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public Book saveBook(@Validated @RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @ExceptionHandler(DataTruncation.class)
    public ResponseEntity<String> handleDataTruncation(DataTruncation ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error trying to process book data:\n" + ex.getMessage().substring(0 , ex.getMessage().length() - 8));
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<String> handlePropertyValue(PropertyValueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error, check if all attributes are filled in correctly");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The book with the same ISBN already exists in the database");
    }
}
