package com.scripturebookapp.verse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VerseNotFoundException extends RuntimeException {
    public VerseNotFoundException() {
        // template literal bb
        super("Verse with that id not found");
    }
} 