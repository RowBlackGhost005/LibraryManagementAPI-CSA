package com.marin.LibraryManagementCSA.service;

import com.marin.LibraryManagementCSA.entity.Book;
import com.marin.LibraryManagementCSA.exceptions.InvalidBookException;
import com.marin.LibraryManagementCSA.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.DataTruncation;
import java.util.List;

@Service
public class BookServiceImp implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(int id) throws InvalidBookException{
        return bookRepository.findById(id).orElseThrow(() -> new InvalidBookException("There is no book with that ID"));

    }

    @Override
    public List<Book> getBooks() {
        return (List<Book>) bookRepository.findAll();
    }


}
