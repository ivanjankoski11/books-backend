package com.example.booksapp.model.dto;

import com.example.booksapp.enumerations.BookCategory;
import com.example.booksapp.model.Author;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookDto {

    private String name;

    private Long authorId;
    private Integer availableCopies;
    private String category;
    private Boolean isLoaned;

    public BookDto () {
    }

    public BookDto (String name, Long authorId, Integer availableCopies, String category,Boolean isLoaned) {
        this.name = name;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
        this.category = category;
        this.isLoaned = isLoaned;
    }

    public BookDto (String name, Long authorId, Integer availableCopies, String category) {
        this.name = name;
        this.authorId = authorId;
        this.availableCopies = availableCopies;
        this.category = category;
    }
}
