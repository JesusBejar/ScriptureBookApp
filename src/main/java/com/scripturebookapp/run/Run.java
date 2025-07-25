package com.scripturebookapp.run;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

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
        if(!started.isAfter(ended)) {
            throw new IllegalArgumentException("Ended on must be after started on");
        }
    }
}
