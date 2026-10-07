package com.example.personal_library.dto;

import com.example.personal_library.model.LibraryEntry;
import com.example.personal_library.model.enumeration.ReadingStatus;

import java.time.Instant;
import java.util.List;

public record DisplayLibraryEntryResponseDto(
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
    public static DisplayLibraryEntryResponseDto from(LibraryEntry entry){
        return new DisplayLibraryEntryResponseDto(
                entry.getId(),
                entry.getBook().getTitle(),
                entry.getBook().getAuthors(),
                entry.getBook().getCoverUrl(),
                entry.getBook().getPublishedYear(),
                entry.getStatus(),
                entry.getRating(),
                entry.getNotes(),
                entry.getCreatedAt(),
                entry.getUpdatedAt()
        );
    }

    public static List<DisplayLibraryEntryResponseDto> from(List<LibraryEntry> entries){
        return entries
                .stream()
                .map(DisplayLibraryEntryResponseDto::from)
                .toList();
    }
}
