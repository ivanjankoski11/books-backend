package com.example.booksapp.model;

import com.example.booksapp.enumerations.BookCategory;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private Author author;
    private Integer availableCopies;
    @Enumerated(EnumType.STRING)
    private BookCategory category;
    private Boolean isLoaned = false;

    public Book () {
    }

    public Book (String name, Author author, Integer availableCopies, BookCategory category) {
        this.name = name;
        this.author = author;
        this.availableCopies = availableCopies;
        this.category = category;
    }
}
