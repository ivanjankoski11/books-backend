package com.example.booksapp.service.impl;

import com.example.booksapp.enumerations.BookCategory;
import com.example.booksapp.model.Author;
import com.example.booksapp.model.Book;
import com.example.booksapp.model.dto.BookDto;
import com.example.booksapp.repository.AuthorRepository;
import com.example.booksapp.repository.BookRepository;
import com.example.booksapp.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl (BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> getAll () {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById (Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow());
    }
    @Override
    public Optional<BookDto> findBookDto(Long id)
    {
        Book book = this.bookRepository.findById(id).orElseThrow();
        BookDto bookDto = new BookDto(book.getName(),book.getAuthor().getId(),book.getAvailableCopies(),book.getCategory().toString());
        return Optional.of(bookDto);
    }
    @Override
    public Optional<Book> save (BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow();
        BookCategory category1 = BookCategory.valueOf(bookDto.getCategory());
        Book book = new Book(bookDto.getName(),author,bookDto.getAvailableCopies(),category1);
        this.bookRepository.save(book);
        return Optional.of(book);
    }


    @Override
    public void delete (Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Optional<BookDto> edit (Long id,BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow();
        book.setName(bookDto.getName());
        Author author = this.authorRepository.findById(bookDto.getAuthorId()).orElseThrow();
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(BookCategory.valueOf(bookDto.getCategory()));
        book.setIsLoaned(bookDto.getIsLoaned());
        BookDto bookDto1 = new BookDto(book.getName(),book.getAuthor().getId(),book.getAvailableCopies(),book.getCategory().toString());
        this.bookRepository.save(book);
        return Optional.of(bookDto1);
    }

    @Override
    public Page<Book> findAllWithPagination (Pageable pageable) {
//        PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("id"))
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public void borrowBook (Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow();
        book.setAvailableCopies(book.getAvailableCopies()-1);
        this.bookRepository.save(book);
    }


}
