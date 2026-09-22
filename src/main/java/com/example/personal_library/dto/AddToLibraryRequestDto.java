package com.example.personal_library.dto;

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
}