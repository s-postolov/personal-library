package com.example.personal_library.web.controller;

import com.example.personal_library.dto.AddToLibraryRequestDto;
import com.example.personal_library.dto.DisplayLibraryEntryResponseDto;
import com.example.personal_library.dto.UpdateLibraryEntryRequestDto;
import com.example.personal_library.service.LibraryEntryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
public class LibraryEntryController {
    LibraryEntryService libraryEntryService;

    public LibraryEntryController(LibraryEntryService libraryEntryService) {
        this.libraryEntryService = libraryEntryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayLibraryEntryResponseDto> findById(@PathVariable Long id){
        return libraryEntryService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DisplayLibraryEntryResponseDto>> findAll(){
        return ResponseEntity.ok(libraryEntryService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayLibraryEntryResponseDto> addToLibrary(
            @RequestBody @Valid AddToLibraryRequestDto addToLibraryRequestDto
    ){
        return ResponseEntity.ok(libraryEntryService.addToLibrary(addToLibraryRequestDto));
    }

    @PostMapping("/{id}/edit")
    public ResponseEntity<DisplayLibraryEntryResponseDto> update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateLibraryEntryRequestDto updateLibraryEntryRequestDto
    ){
        return libraryEntryService
                .update(id, updateLibraryEntryRequestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayLibraryEntryResponseDto> deleteById(@PathVariable Long id){
        return libraryEntryService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}