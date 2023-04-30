package com.example.booksapp.web;

import com.example.booksapp.model.Author;
import com.example.booksapp.model.dto.AuthorDto;
import com.example.booksapp.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequestMapping("/author")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController (AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody AuthorDto authorDto){
        return this.authorService.save(authorDto)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<Author> getAuthors(){
        return this.authorService.getAll();
    }

}
