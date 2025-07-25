package com.scripturebookapp.verse;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

// annotations are king
public record Verse (
    Integer id, 
    @NotEmpty
    String book,    
    @Positive
    Integer chapter,   
    @Positive  
    Integer verseNum,  
    @NotEmpty
    String text,       
    @NotEmpty
    String translation  
)
{
    public Verse {
        // Bible-specific validation
        if(chapter < 1 || verseNum < 1) {
            throw new IllegalArgumentException("Chapter and verse numbers must be positive");
        }
    }
} 