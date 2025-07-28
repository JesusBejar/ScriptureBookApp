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
        // load existing verses from file first
        readFromFile();
        
        // if no verses, add default verses
        if (verses.isEmpty()) {
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
            
            // Save default verses to file
            writeToFile();
        }
    }

    // write verses to json file
    private void writeToFile() {
        try {
            // create file if it doesn't exist
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            
            // loop through verses before writing
            List<Verse> versesToWrite = new ArrayList<>();
            for (int i = 0; i < verses.size(); i++) {
                Verse currentVerse = verses.get(i);
                // validation while looping
                if (currentVerse != null && currentVerse.text() != null && !currentVerse.text().trim().isEmpty()) {
                    versesToWrite.add(currentVerse);
                }
            }
            
            try (FileWriter fileWriter = new FileWriter(file)) {
                String jsonString = objectMapper.writeValueAsString(versesToWrite);
                fileWriter.write(jsonString);
                fileWriter.flush();
            }
            
            System.out.println("Successfully wrote " + versesToWrite.size() + " verses to " + FILE_PATH);
            
            // fancy error handling
        } catch (IOException error) {
            // Error handling for file operations
            System.err.println("Error writing to file: " + error.getMessage());
            throw new RuntimeException("Failed to write verses to file", error);
        } catch (Exception error) {
            // General error handling
            System.err.println("Unexpected error during file write: " + error.getMessage());
            throw new RuntimeException("Unexpected error during file write", error);
        }
    }

    // read verses from json file
    private void readFromFile() {
        try {
            File file = new File(FILE_PATH);
            
            // existence if-check
            if (!file.exists()) {
                System.out.println("File " + FILE_PATH + " does not exist. Starting with empty verse list.");
                return;
            }
            
            // empty if-check
            if (file.length() == 0) {
                System.out.println("File " + FILE_PATH + " is empty. Starting with empty verse list.");
                return;
            }
            
            // read
            String jsonContent = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            
            // parse
            List<Verse> loadedVerses = objectMapper.readValue(jsonContent, new TypeReference<List<Verse>>() {});
            
            // clear
            verses.clear();
            // loop through verses within
            for (int i = 0; i < loadedVerses.size(); i++) {
                Verse currentVerse = loadedVerses.get(i);
                // Validate each verse while looping
                if (currentVerse != null && currentVerse.id() != null) {
                    verses.add(currentVerse);
                }
            }
            
            System.out.println("File loaded " + verses.size() + " verses from " + FILE_PATH);
            
        } catch (IOException e) {
            // error handling
            System.err.println("Error: " + e.getMessage());
            System.out.println("Starting with empty list");
        } catch (Exception e) {
            // more error handling
            System.err.println("Error: " + e.getMessage());
            System.out.println("Starting with empty list");
        }
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
        // auto-save
        writeToFile();
    }

    // endpoint to update verse
    // update(Verse verse, int id)
    void update(Verse verse, @PathVariable Integer id) {
        // optional annotation is a mini if-check
        Optional<Verse> existingVerse = findById(id);
        if (existingVerse.isPresent()) {
            verses.set(verses.indexOf(existingVerse.get()), verse);
            // auto-save
            writeToFile();
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
        // auto-save
        writeToFile();
    }
} 