package com.example.personal_library;

import com.example.personal_library.model.Book;
import com.example.personal_library.model.LibraryEntry;
import com.example.personal_library.model.enumeration.ReadingStatus;
import com.example.personal_library.repository.BookRepository;
import com.example.personal_library.repository.LibraryEntryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class RepositoryIntegrationTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryEntryRepository libraryEntryRepository;

    @Test
    void savesBookAndLibraryEntryTogether(){
        Book book = new Book(
                "The Hobbit",
                "OL1W",
                List.of("J.R.R. Tolkien"),
                "9780547928227",
                null,
                1937,
                "A fantasy novel"
        );
        bookRepository.save(book);

        LibraryEntry entry = new LibraryEntry(
                book, ReadingStatus.WANT_TO_READ, null, null
        );
        libraryEntryRepository.save(entry);

        assertThat(entry.getId()).isNotNull();
        assertThat(entry.getBook().getTitle()).isEqualTo("The Hobbit");
    }
}
