package com.example.booksapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

//id (Long), name (String), surname (String), country (Country).
@Data
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Country country;

    public Author () {
    }

    public Author (String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
