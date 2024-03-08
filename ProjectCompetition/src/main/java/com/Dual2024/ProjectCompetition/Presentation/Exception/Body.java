package com.Dual2024.ProjectCompetition.Presentation.Exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * The body error.
 *
 * @author Franciosco Balonero Olivera
 */
@Setter
@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class Body {
    @NonNull
    private HttpStatus state;
    @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime date = LocalDateTime.now();
    @NonNull
    private String message;
}
