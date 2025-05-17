package com.marin.LibraryManagementCSA.service;

import com.marin.LibraryManagementCSA.entity.Book;
import com.marin.LibraryManagementCSA.exceptions.InvalidBookException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    List<Book> getBooks();

    Book getBookById(int id) throws InvalidBookException;

    Book updateBook(Book book , int id) throws InvalidBookException;

    void deleteBook(int id);

    List<Book> getBookAuthor(String author);

    List<Book> getBookTitle(String title);
}
