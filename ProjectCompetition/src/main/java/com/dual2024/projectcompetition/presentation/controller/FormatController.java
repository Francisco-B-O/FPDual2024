package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessexception.DuplicatedNameException;
import com.dual2024.projectcompetition.business.businessexception.FormatNotFoundException;
import com.dual2024.projectcompetition.business.businessobject.FormatBO;
import com.dual2024.projectcompetition.business.service.FormatService;
import com.dual2024.projectcompetition.presentation.dto.FormatDTO;
import com.dual2024.projectcompetition.presentation.dto.RegisterFormatDTO;
import com.dual2024.projectcompetition.presentation.dto.converters.BOToDTOConverter;
import com.dual2024.projectcompetition.presentation.dto.converters.DTOToBOConverter;
import com.dual2024.projectcompetition.presentation.exception.NotFoundException;
import com.dual2024.projectcompetition.presentation.exception.PresentationException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for managing formats.
 *
 * <p>This class defines RESTful endpoints for performing CRUD operations on formats. It handles
 * requests related to retrieving all formats, retrieving a format by ID or name, adding, updating,
 * and deleting formats. The endpoints are secured, and only authorized users with specific roles
 * can access them.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see FormatService
 * @see BOToDTOConverter
 * @see DTOToBOConverter
 */
@RequestMapping("format")
@RestController
@Tag(name = "Format", description = "Operations related to formats management")
public class FormatController {
    /**
     * The Bo to dto converter.
     */
    @Autowired
    BOToDTOConverter boToDTOConverter;
    /**
     * The Dto to bo converter.
     */
    @Autowired
    DTOToBOConverter dtoToBOConverter;
    @Autowired
    private FormatService formatService;

    /**
     * Retrieves all formats.
     *
     * @return {@link List} List of all formats
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get all formats")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<FormatDTO> getAllFormats() throws PresentationException {
        List<FormatDTO> listFormatDTO = new ArrayList<FormatDTO>();
        try {
            formatService.getAllFormats()
                    .forEach((FormatBO format) -> listFormatDTO.add(boToDTOConverter.formatBOToDTO(format)));
            return listFormatDTO;
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Retrieves a format by its ID.
     *
     * @param id {@link Long} The ID of the format
     * @return {@link FormatDTO} The format with the specified ID
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get format by ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public FormatDTO getFormatById(@PathVariable("id") @Parameter(description = "The ID of the format") Long id) throws PresentationException {
        try {
            return boToDTOConverter.formatBOToDTO(formatService.getFormatById(id));
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Adds a new format.
     *
     * @param format {@link RegisterFormatDTO} The format to be added
     * @return {@link FormatDTO} The added format
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Add a new format")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public FormatDTO addFormat(@RequestBody @Valid RegisterFormatDTO format) throws PresentationException {
        try {
            return boToDTOConverter.formatBOToDTO(formatService.addFormat(dtoToBOConverter.RegisterFormatDTOToBO(format)));
        } catch (DuplicatedNameException e) {
            throw new PresentationException(e.getMessage());
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Deletes a format.
     *
     * @param id {@link Long} The ID of the format to be deleted
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Delete a format")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteFormat(@PathVariable @Parameter(description = "The ID of the format to be deleted") Long id) throws PresentationException {
        try {
            formatService.deleteFormat(id);
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Retrieves a format by its name.
     *
     * @param name {@link String} The name of the format
     * @return {@link FormatDTO} The format with the specified name
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get format by name")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_PLAYER')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name/{name}")
    public FormatDTO getFormatByName(@PathVariable("name") @Parameter(description = "The name of the format") String name) throws PresentationException {
        try {
            return boToDTOConverter.formatBOToDTO(formatService.getFormatByName(name));
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Updates a format.
     *
     * @param format {@link FormatDTO} The updated format
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Update a format")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/update")
    public void updateFormat(@RequestBody @Valid FormatDTO format) throws PresentationException {
        try {
            formatService.updateFormat(dtoToBOConverter.formatDTOToBO(format));
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }
}
