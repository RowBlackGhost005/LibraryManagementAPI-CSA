package com.marin.LibraryManagementCSA.controller;

import com.marin.LibraryManagementCSA.entity.Book;
import com.marin.LibraryManagementCSA.exceptions.InvalidBookException;
import com.marin.LibraryManagementCSA.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.hibernate.PropertyValueException;

import java.sql.DataTruncation;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Books API", description = "Operations related to books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    @Operation(summary = "Saves the provided Book", description = "Provide a book in the body with all its parameters except ID")
    public Book saveBook(@Validated @RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping
    @Operation(summary = "Return all registered books", description = "Return all books in stored in this API as a single JSON")
    public List<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetches the book with the ID", description = "Fetches the book with the ID passed as path variable, if there is no book with such ID returns a message stating it")
    public Book getBookById(@PathVariable int id) throws InvalidBookException{
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a book by its ID with the data provided", description = "Fetch a book with the ID given as path variable and if exists updates the information sent in the body")
    public Book updateBook(@RequestBody Book book , @PathVariable int id) throws InvalidBookException{
        return bookService.updateBook(book , id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes the book with an ID", description = "Deletes the book that has the same ID has the path variable")
    public String deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return "Book with ID " + id + " has been deleted";
    }

    @GetMapping("/authors/{author}")
    @Operation(summary = "Fetches all books by author", description = "Fetches all books that have the author given as path variable")
    public List<Book> getBookAuthor(@PathVariable String author){
        return bookService.getBookAuthor(author);
    }

    @GetMapping("/titles/{title}")
    @Operation(summary = "Fetches all books by title", description = "Fetches all books that have a matching String given as path variable in any position")
    public List<Book> getBookTitle(@PathVariable String title){
        return bookService.getBookTitle(title);
    }

    @ExceptionHandler(DataTruncation.class)
    public ResponseEntity<String> handleDataTruncation(DataTruncation ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error trying to process book data:\n" + ex.getMessage().substring(0 , ex.getMessage().length() - 8));
    }

    @ExceptionHandler(PropertyValueException.class)
    public ResponseEntity<String> handlePropertyValue(PropertyValueException ex) {
        ex.printStackTrace(System.err);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error, check if all attributes are filled in correctly");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("The book with the same ISBN already exists in the database");
    }

    @ExceptionHandler(InvalidBookException.class)
    public ResponseEntity<String> handleInvalidBook(InvalidBookException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleInvalidBody(HttpMessageNotReadableException ex) {
        System.err.println(ex.getMessage());
        System.err.println(ex.getCause());
        return ResponseEntity.badRequest().body("Invalid request body");
    }


}
