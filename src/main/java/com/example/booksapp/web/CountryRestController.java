package com.example.booksapp.web;

import com.example.booksapp.model.Country;
import com.example.booksapp.service.CountryService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/countries")
public class CountryRestController {
    private final CountryService countryService;

    public CountryRestController (CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping()
    public List<Country> getAllCountries(){
        return this.countryService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> addCountry(@RequestBody Country country)
    {
       return this.countryService.save(country)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
