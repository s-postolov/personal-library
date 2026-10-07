package com.example.personal_library.repository;

import com.example.personal_library.model.LibraryEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryEntryRepository extends JpaRepository<LibraryEntry, Long> {
    Boolean existsByBookId(Long bookId);
}
