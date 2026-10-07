package com.example.personal_library.service;

import com.example.personal_library.dto.AddToLibraryRequestDto;
import com.example.personal_library.dto.DisplayLibraryEntryResponseDto;
import com.example.personal_library.dto.UpdateLibraryEntryRequestDto;
import com.example.personal_library.model.Book;
import com.example.personal_library.model.LibraryEntry;
import com.example.personal_library.model.enumeration.ReadingStatus;
import com.example.personal_library.model.exceptions.BookAlreadyExistsException;
import com.example.personal_library.repository.BookRepository;
import com.example.personal_library.repository.LibraryEntryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibraryEntryService {
    private final BookRepository bookRepository;
    private final LibraryEntryRepository libraryEntryRepository;

    public LibraryEntryService(BookRepository bookRepository, LibraryEntryRepository libraryEntryRepository) {
        this.bookRepository = bookRepository;
        this.libraryEntryRepository = libraryEntryRepository;
    }

    public List<DisplayLibraryEntryResponseDto> findAll(){
        return DisplayLibraryEntryResponseDto.from(libraryEntryRepository.findAll());
    }

    public Optional<DisplayLibraryEntryResponseDto> findById(Long id){
        return libraryEntryRepository
                .findById(id)
                .map(DisplayLibraryEntryResponseDto::from);
    }

    public DisplayLibraryEntryResponseDto addToLibrary(AddToLibraryRequestDto addToLibraryRequestDto){
        Book book = bookRepository
                .findByExternalId(addToLibraryRequestDto.externalId())
                .orElseGet(() -> bookRepository.save(addToLibraryRequestDto.toBook()));

        if(libraryEntryRepository.existsByBookId(book.getId())){
            throw new BookAlreadyExistsException(book.getId());
        }

        LibraryEntry entry = new LibraryEntry(book, ReadingStatus.WANT_TO_READ, null, null);
        libraryEntryRepository.save(entry);

        return DisplayLibraryEntryResponseDto.from(entry);
    }

    public Optional<DisplayLibraryEntryResponseDto> update(Long id, UpdateLibraryEntryRequestDto updateLibraryEntryRequestDto){
        return libraryEntryRepository
                .findById(id)
                .map(entry -> {
                    entry.setStatus(updateLibraryEntryRequestDto.status());
                    entry.setRating(updateLibraryEntryRequestDto.rating());
                    entry.setNotes(updateLibraryEntryRequestDto.notes());
                    return DisplayLibraryEntryResponseDto.from(entry);
                });
    }

    public Optional<DisplayLibraryEntryResponseDto> deleteById(Long id){
        Optional<LibraryEntry> entry = libraryEntryRepository.findById(id);
        entry.ifPresent(libraryEntryRepository::delete);
        return entry.map(DisplayLibraryEntryResponseDto::from);
    }
}
