package com.dual2024.projectcompetition.presentation.controller;

import com.dual2024.projectcompetition.business.businessexception.BusinessException;
import com.dual2024.projectcompetition.business.businessexception.DuplicatedNameException;
import com.dual2024.projectcompetition.business.businessexception.ModalityNotFoundException;
import com.dual2024.projectcompetition.business.businessobject.ModalityBO;
import com.dual2024.projectcompetition.business.service.ModalityService;
import com.dual2024.projectcompetition.presentation.dto.ModalityDTO;
import com.dual2024.projectcompetition.presentation.dto.RegisterModalityDTO;
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
 * Controller class for managing modalities.
 *
 * <p>This class defines RESTful endpoints for performing CRUD operations on modalities. It handles
 * requests related to retrieving all modalities, retrieving a modality by ID, name, or number of players,
 * adding, updating, and deleting modalities. The endpoints are secured, and only authorized users with
 * specific roles can access them.</p>
 *
 * @author Franciosco Balonero Olivera
 * @see ModalityService
 * @see BOToDTOConverter
 * @see DTOToBOConverter
 */
@RequestMapping("modality")
@RestController
@Tag(name = "Modality", description = "Operations related to modalities management")
public class ModalityController {
    @Autowired
    private BOToDTOConverter boToDTOConverter;
    @Autowired
    private DTOToBOConverter dtoToBOConverter;
    @Autowired
    private ModalityService modalityService;

    /**
     * Retrieves all modalities in the system.
     *
     * @return {@link List} List of all modalities
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get all modalities")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<ModalityDTO> getAllModalities() throws PresentationException {
        List<ModalityDTO> listModalityDTO = new ArrayList<>();
        try {
            modalityService.getAllModalities()
                    .forEach((ModalityBO modality) -> listModalityDTO.add(boToDTOConverter.modalityBOToDTO(modality)));
            return listModalityDTO;
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Retrieves a modality by its ID.
     *
     * @param id {@link Long} The ID of the modality
     * @return {@link ModalityDTO} The modality with the specified ID
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get modality by ID")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public ModalityDTO getModalityById(@PathVariable("id") @Parameter(description = "ID of the modality") Long id) throws PresentationException {
        try {
            return boToDTOConverter.modalityBOToDTO(modalityService.getModalityById(id));
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Adds a new modality.
     *
     * @param modality {@link RegisterModalityDTO} The modality to be added
     * @return {@link ModalityDTO} The added modality
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Add a new modality")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public ModalityDTO addModality(@RequestBody @Valid RegisterModalityDTO modality) throws PresentationException {
        try {
            return boToDTOConverter
                    .modalityBOToDTO(modalityService.addModality(dtoToBOConverter.RegisterModalityDTOToBO(modality)));
        } catch (DuplicatedNameException e) {
            throw new PresentationException(e.getMessage());
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Deletes a modality.
     *
     * @param id {@link Long} The ID of the modality to be deleted
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Delete a modality")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteModality(@PathVariable @Parameter(description = "ID of the modality to be deleted") Long id) throws PresentationException {
        try {
            modalityService.deleteModality(id);
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Retrieves a modality by its name.
     *
     * @param name {@link String} The name of the modality
     * @return {@link ModalityDTO} The modality with the specified name
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get modality by name")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name/{name}")
    public ModalityDTO getModalityByName(@PathVariable("name") @Parameter(description = "Name of the modality") String name) throws PresentationException {
        try {
            return boToDTOConverter.modalityBOToDTO(modalityService.getModalityByName(name));
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Retrieves all modalities based on the number of players.
     *
     * @param players {@link Integer} The number of players
     * @return {@link List} List of modalities with the specified number of players
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Get all modalities by number of players")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/players/{players}")
    public List<ModalityDTO> getAllModalities(@PathVariable("players") @Parameter(description = "Number of players") int players) throws PresentationException {
        List<ModalityDTO> listModalityDTO = new ArrayList<>();
        try {
            modalityService.getModalitiesByNumberPlayers(players)
                    .forEach((ModalityBO modality) -> listModalityDTO.add(boToDTOConverter.modalityBOToDTO(modality)));
            return listModalityDTO;
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Updates an existing modality.
     *
     * @param modality {@link ModalityDTO} The updated modality
     * @throws PresentationException if an error occurs during presentation
     */
    @Operation(summary = "Update an existing modality")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping("/update")
    public void updateModality(@RequestBody @Valid ModalityDTO modality) throws PresentationException {
        try {
            modalityService.updateModality(dtoToBOConverter.modalityDTOToBO(modality));
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }
}
