package com.marin.LibraryManagementCSA.repository;

import com.marin.LibraryManagementCSA.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer>{

    List<Book> getBooksByAuthor(String author);

    @Query("SELECT b FROM Book b WHERE b.title LIKE CONCAT('%', :title , '%')")
    List<Book> getBooksByTitle(@Param("title") String title);
}
