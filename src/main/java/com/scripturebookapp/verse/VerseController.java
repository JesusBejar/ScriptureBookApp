package com.scripturebookapp.verse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/verses")
class VerseController {

    private final VerseRepo verseRepo;

    VerseController(VerseRepo verseRepo) {
        this.verseRepo = verseRepo;
    }

    // find all
    @GetMapping
    List<Verse> findAll() {
        // method from VerseRepo
        return verseRepo.findAll();
    }

    // find by id
    @GetMapping("/{id}")
    Verse findById(@PathVariable Integer id) {
        // method from VerseRepo
        Optional<Verse> verse = verseRepo.findById(id);
        if(verse.isEmpty()) {
            throw new VerseNotFoundException();
        }
        return verse.get();
    }

    // create
    @ResponseStatus(HttpStatus.CREATED) // 201 code, look for it in postman
    // annotations are necessary for successful requests
    @PostMapping
    void create(@Valid @RequestBody Verse verse) {
        // method from VerseRepo
        verseRepo.create(verse);
    }

    // update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    // request body annotation connects the request body to path
    // path variable annotation connects dynamic id to path
    void update(@Valid @RequestBody Verse verse, @PathVariable Integer id) {
        // method from VerseRepo
        verseRepo.update(verse,id);
    }

    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@Valid @PathVariable Integer id) {
        // method from VerseRepo
        verseRepo.delete(id);
    }

} 