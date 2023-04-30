package com.example.booksapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// id (Long), name (String), continent (String)
@Data
@Entity
public class Country {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String continent;

    public Country () {
    }

    public Country (String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
