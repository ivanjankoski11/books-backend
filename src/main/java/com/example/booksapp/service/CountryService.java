package com.example.booksapp.service;

import com.example.booksapp.model.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> getAll();
    Optional<Country> save(Country country);
}
