package com.scripturebookapp.run;

import java.time.LocalDateTime;

public record Run (
    Integer id, 
    String title, 
    LocalDateTime started, 
    LocalDateTime ended,
    Integer miles
)
{}
