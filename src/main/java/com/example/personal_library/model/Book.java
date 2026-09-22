package com.example.personal_library.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String externalId; //id of the book in open library's system ex. /works/OL45805W (work key) or /books/OL7353617M (edition key)

    @ElementCollection
    private List<String> authors;

    private String isbn13;

    private String coverUrl;

    private Integer publishedYear;

    @Column(length = 20000)
    private String description;

    public Book(String title, String externalId, List<String> authors, String isbn13, String coverUrl, Integer publishedYear, String description) {
        this.title = title;
        this.externalId = externalId;
        this.authors = authors;
        this.isbn13 = isbn13;
        this.coverUrl = coverUrl;
        this.publishedYear = publishedYear;
        this.description = description;
    }
}