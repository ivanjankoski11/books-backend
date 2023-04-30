package com.example.booksapp.service;

import com.example.booksapp.enumerations.BookCategory;
import com.example.booksapp.model.Author;
import com.example.booksapp.model.Book;
import com.example.booksapp.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAll();
    Optional<Book> findById(Long id);

    Optional<Book> save (BookDto bookDto);

    Optional<BookDto> edit(Long id,BookDto bookDto);
    Page<Book> findAllWithPagination(Pageable pageable);
    Optional<BookDto> findBookDto(Long id);

    void borrowBook(Long id);

    void delete(Long id);

}
