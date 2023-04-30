package com.example.booksapp.service.impl;

import com.example.booksapp.model.Author;
import com.example.booksapp.model.Country;
import com.example.booksapp.model.dto.AuthorDto;
import com.example.booksapp.repository.AuthorRepository;
import com.example.booksapp.repository.CountryRepository;
import com.example.booksapp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl (AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> getAll () {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save (AuthorDto author) {
        Country country = this.countryRepository.findById(author.getCountryId()).orElseThrow();
        Author author1 = new Author(author.getName(),author.getSurname(),country);
        this.authorRepository.save(author1);
        return Optional.of(author1);
    }
}
