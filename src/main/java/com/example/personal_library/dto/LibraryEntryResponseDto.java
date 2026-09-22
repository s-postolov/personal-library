package com.example.personal_library.dto;

import com.example.personal_library.model.enumeration.ReadingStatus;

import java.time.Instant;
import java.util.List;

public record LibraryEntryResponseDto(
        Long id,
        String title,
        List<String> authors,
        String coverUrl,
        Integer publishedYear,
        ReadingStatus status,
        Integer rating,
        String notes,
        Instant createdAt,
        Instant updatedAt
) {
}
