package com.dual2024.projectcompetition.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * DTO representing a data format.
 *
 * @author Franciosco Balonero Olivera
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Schema(description = "DTO representing a data format")
public class FormatDTO {
    @Schema(description = "Unique identifier of the format", example = "1")
    private Long id;

    @Schema(description = "Name of the format", example = "Play Off")
    private String name;
}
