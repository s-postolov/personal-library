package com.example.personal_library.dto;

import com.example.personal_library.model.enumeration.ReadingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UpdateLibraryEntryRequestDto(
        ReadingStatus status,
        @Min(1) @Max(5) Integer rating,
        String notes
) {
}
