package com.Dual2024.ProjectCompetition.Presentation.Controller;

import com.Dual2024.ProjectCompetition.Business.BusinessException.BusinessException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.DuplicatedNameException;
import com.Dual2024.ProjectCompetition.Business.BusinessException.ModalityNotFoundException;
import com.Dual2024.ProjectCompetition.Business.BusinessObject.ModalityBO;
import com.Dual2024.ProjectCompetition.Business.Service.ModalityService;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.Converters.BOToDTOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.Converters.DTOToBOConverter;
import com.Dual2024.ProjectCompetition.Presentation.DataTransferObject.ModalityDTO;
import com.Dual2024.ProjectCompetition.Presentation.Exception.NotFoundException;
import com.Dual2024.ProjectCompetition.Presentation.Exception.PresentationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Modality controller.
 *
 * @author Franciosco Balonero Olivera
 */
@RequestMapping("modality")
@RestController
public class ModalityController {
    @Autowired
    private BOToDTOConverter boToDTOConverter;
    @Autowired
    private DTOToBOConverter dtoToBOConverter;
    @Autowired
    private ModalityService modalityService;

    /**
     * Gets all modalities.
     *
     * @return the all modalities
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/all")
    public List<ModalityDTO> getAllModalities() throws PresentationException {
        List<ModalityDTO> listModalityDTO = new ArrayList<ModalityDTO>();
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
     * Gets modality by id.
     *
     * @param id the id
     * @return the modality by id
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public ModalityDTO getModalityById(@PathVariable("id") Long id) throws PresentationException {
        try {
            return boToDTOConverter.modalityBOToDTO(modalityService.getModalityById(id));
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Add modality modality dto.
     *
     * @param modality the modality
     * @return the modality dto
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/add")
    public ModalityDTO addModality(@RequestBody @Valid ModalityDTO modality) throws PresentationException {

        try {
            return boToDTOConverter
                    .modalityBOToDTO(modalityService.addModality(dtoToBOConverter.modalityDTOToBO(modality)));
        } catch (DuplicatedNameException e) {
            throw new PresentationException(e.getMessage());
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Delete modality.
     *
     * @param id the id
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @DeleteMapping("/delete/{id}")
    public void deleteModality(@PathVariable Long id) {
        try {
            modalityService.deleteModality(id);
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }

    /**
     * Gets modality by name.
     *
     * @param name the name
     * @return the modality by name
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/name/{name}")
    public ModalityDTO getModalityByName(@PathVariable("name") String name) throws PresentationException {
        try {
            return boToDTOConverter.modalityBOToDTO(modalityService.getModalityByName(name));
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }

    }

    /**
     * Gets all modalities.
     *
     * @param players the players
     * @return the all modalities
     * @throws PresentationException the presentation exception
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR') or hasRole('ROLE_JUGADOR')")
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/players/{players}")
    public List<ModalityDTO> getAllModalities(@PathVariable("players") int players) throws PresentationException {
        List<ModalityDTO> listModalityDTO = new ArrayList<ModalityDTO>();
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
     * Update modality.
     *
     * @param modality the modality
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_GESTOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping("/update")
    public void updateModality(@RequestBody @Valid ModalityDTO modality) {
        try {
            modalityService.updateModality(dtoToBOConverter.modalityDTOToBO(modality));
        } catch (ModalityNotFoundException e) {
            throw new NotFoundException(e.getMessage(), e);
        } catch (BusinessException e) {
            throw new PresentationException(e.getMessage(), e);
        }
    }
}
