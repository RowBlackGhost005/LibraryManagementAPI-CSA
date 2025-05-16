package com.marin.LibraryManagementCSA.repository;

import com.marin.LibraryManagementCSA.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
}
