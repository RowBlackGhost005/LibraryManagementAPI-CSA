package com.marin.LibraryManagementCSA.service;

import com.marin.LibraryManagementCSA.entity.Book;
import com.marin.LibraryManagementCSA.exceptions.InvalidBookException;

import java.util.List;

public interface BookService {

    Book saveBook(Book book);

    List<Book> getBooks();

    Book getBookById(int id) throws InvalidBookException;

    Book updateBook(Book book , int id) throws InvalidBookException;
}
