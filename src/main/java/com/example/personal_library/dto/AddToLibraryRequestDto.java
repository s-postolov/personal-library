package com.example.personal_library.dto;

import com.example.personal_library.model.Book;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record AddToLibraryRequestDto(
        @NotBlank String title,
        @NotBlank String externalId,
        List<String> authors,
        String isbn13,
        String coverUrl,
        Integer publishedYear,
        String description
) {
    public Book toBook(){
        return new Book(title, externalId, authors, isbn13, coverUrl, publishedYear, description);
    }
}