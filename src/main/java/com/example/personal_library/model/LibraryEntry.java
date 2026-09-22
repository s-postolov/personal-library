package com.example.personal_library.model;

import com.example.personal_library.model.enumeration.ReadingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LibraryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false, unique = true)
    private Book book;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ReadingStatus status;

    private Integer rating;

    @Column(length = 20000)
    private String notes;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Instant updatedAt;

    public LibraryEntry(Book book, ReadingStatus status, Integer rating, String notes) {
        this.book = book;
        this.status = status;
        this.rating = rating;
        this.notes = notes;
    }
}
