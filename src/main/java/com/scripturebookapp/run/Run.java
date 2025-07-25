package com.scripturebookapp.run;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

// annotations are king
public record Run (
    Integer id, 
    @NotEmpty
    String title, 
    LocalDateTime started, 
    LocalDateTime ended,
    @Positive
    Integer miles
)
{
    public Run {
        if(!ended.isAfter(started)) {
            throw new IllegalArgumentException("Ended on must be after started on");
        }
    }
}
