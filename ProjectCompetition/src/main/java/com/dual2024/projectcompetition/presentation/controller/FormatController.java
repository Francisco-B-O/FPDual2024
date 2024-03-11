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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The Format controller.
 */
@RequestMapping("format")
@RestController
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
     * Gets all formats.
     *
     * @return All formats
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
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
     * Gets format by id.
     *
     * @param id The id
     * @return The format by id
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public FormatDTO getFormatById(@PathVariable("id") Long id) throws PresentationException {
        try {
            return boToDTOConverter.formatBOToDTO(formatService.getFormatById(id));
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Add format.
     *
     * @param format The format
     * @return The format dto
     * @throws PresentationException the presentation exception
     */
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
     * Delete format.
     *
     * @param id The id
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteFormat(@PathVariable Long id) {
        try {
            formatService.deleteFormat(id);
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets format by name.
     *
     * @param name The name
     * @return The format by name
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name/{name}")
    public FormatDTO getFormatByName(@PathVariable("name") String name) throws PresentationException {
        try {
            return boToDTOConverter.formatBOToDTO(formatService.getFormatByName(name));
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Update format.
     *
     * @param format The updated format
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @PutMapping("/update")
    public void updateFormat(@RequestBody @Valid FormatDTO format) {
        try {
            formatService.updateFormat(dtoToBOConverter.formatDTOToBO(format));
        } catch (FormatNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }
}
