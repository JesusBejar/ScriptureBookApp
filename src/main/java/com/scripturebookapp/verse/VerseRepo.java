package com.scripturebookapp.verse;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import jakarta.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

@Repository
public class VerseRepo {
    private final List<Verse> verses = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String FILE_PATH = "verses.json";

    // verse data in-house
    @PostConstruct
    private void init() {
        verses.add(new Verse(
            1,
            "John",
            3,
            16,
            "For God so loved the world that he gave his one and only Son, that whoever believes in him shall not perish but have eternal life.",
            "NIV"
        ));
        verses.add(new Verse(
            2,
            "Genesis", 
            1,
            1,
            "In the beginning God created the heavens and the earth.",
            "NIV"
        ));
    }

    // endpoint to return array of verses
    // findAll()
    @GetMapping("/verses")
    List<Verse> findAll() {
        return verses;
    }
    // endpoint to return specific verse
    // findById(int id)
    @GetMapping("/verses/{id}")
    // PathVariable makes int dynamic
    Optional<Verse> findById(@PathVariable Integer id) {
        return verses.stream()
        .filter(v -> v.id()
        .equals(id))
        .findFirst();
    }
    // endpoint to make verse
    // create(Verse verse)
    void create(Verse verse) {
        verses.add(verse);
    }

    // endpoint to update verse
    // update(Verse verse, int id)
    void update(Verse verse, @PathVariable Integer id) {
        // optional annotation is a mini if-check
        Optional<Verse> existingVerse = findById(id);
        if (existingVerse.isPresent()) {
            verses.set(verses.indexOf(existingVerse.get()), verse);
        }
    }

    // endpoint to delete verse
    // delete(int id)
    void delete(@PathVariable Integer id) {
        Optional<Verse> verse = findById(id);
        // if check
        if (verse.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        verses.removeIf(v -> v.id().equals(id));
    }
} 