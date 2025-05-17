package com.marin.LibraryManagementCSA.service;

import com.marin.LibraryManagementCSA.entity.Book;
import com.marin.LibraryManagementCSA.exceptions.InvalidBookException;
import com.marin.LibraryManagementCSA.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Override
    public Book updateBook(Book book , int id) throws InvalidBookException{
        Book bookDB = getBookById(id);

        if(book.getAuthor() != null){
            bookDB.setAuthor(book.getAuthor());
        }

        if(book.getTitle() != null){
            bookDB.setTitle(book.getTitle());
        }

        if(book.getPublishedDate() != null){
            bookDB.setPublishedDate(book.getPublishedDate());
        }

        if(book.getIsbn() != null){
            bookDB.setIsbn(book.getIsbn());
        }

        if(book.getGenre() != null){
            bookDB.setGenre(book.getGenre());
        }

        return bookRepository.save(bookDB);
    }

    @Override
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getBookAuthor(String author){
        return bookRepository.getBooksByAuthor(author);
    }

    @Override
    public List<Book> getBookTitle(String title){
        return bookRepository.getBooksByTitle(title);
    }

}
