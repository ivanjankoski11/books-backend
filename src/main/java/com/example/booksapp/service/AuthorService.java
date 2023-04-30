package com.example.booksapp.service;

import com.example.booksapp.model.Author;
import com.example.booksapp.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;


public interface AuthorService {
    List<Author> getAll();
    Optional<Author> save(AuthorDto author);
}
