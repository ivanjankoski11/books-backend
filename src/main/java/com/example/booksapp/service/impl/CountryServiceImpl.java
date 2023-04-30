package com.example.booksapp.service.impl;

import com.example.booksapp.model.Country;
import com.example.booksapp.repository.CountryRepository;
import com.example.booksapp.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl (CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    @Override
    public List<Country> getAll () {
        return this.countryRepository.findAll();
    }

    @Override
    public Optional<Country> save (Country country) {
        this.countryRepository.save(country);
        return Optional.of(country);
    }
}
